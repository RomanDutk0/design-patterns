package com.epam.rd.autocode.observer.git;

import java.util.*;

public class GitRepoObservers {

    // Factory method to create a new Repository
    public static Repository newRepository() {
        return new GitRepository(); // Return an instance of the concrete repository
    }

    // Factory method to create a WebHook that observes merge events
    public static WebHook mergeToBranchWebHook(String branchName) {
        return new BranchWebHook(branchName, Event.Type.MERGE);
    }

    // Factory method to create a WebHook that observes commit events
    public static WebHook commitToBranchWebHook(String branchName) {
        return new BranchWebHook(branchName, Event.Type.COMMIT);
    }

    // Implementation of Repository (Observer pattern Subject)
    private static class GitRepository implements Repository {
        private final Map<String, List<Commit>> branches = new HashMap<>();
        private final List<WebHook> webHooks = new ArrayList<>();

        // Add a WebHook to observe events
        @Override
        public void addWebHook(WebHook webHook) {
            webHooks.add(webHook);
        }

        // Create a commit in a branch
        @Override
        public Commit commit(String branch, String author, String[] changes) {
            Commit newCommit = new Commit(author, changes);
            branches.putIfAbsent(branch, new ArrayList<>());
            branches.get(branch).add(newCommit);

            // Notify WebHooks observing commits
            notifyWebHooks(new Event(Event.Type.COMMIT, branch, Collections.singletonList(newCommit)));

            return newCommit;
        }

        // Merge commits from sourceBranch to targetBranch
        public void merge(String sourceBranch, String targetBranch) {
            List<Commit> sourceCommits = branches.getOrDefault(sourceBranch, Collections.emptyList());
            List<Commit> targetCommits = branches.getOrDefault(targetBranch, Collections.emptyList());

            // Only add commits that are not already present in the target branch
            List<Commit> newCommits = new ArrayList<>();
            for (Commit commit : sourceCommits) {
                if (!targetCommits.contains(commit)) {
                    newCommits.add(commit);
                }
            }

            branches.putIfAbsent(targetBranch, new ArrayList<>());
            branches.get(targetBranch).addAll(newCommits);

            // Notify WebHooks observing merges
            if (!newCommits.isEmpty()) {
                notifyWebHooks(new Event(Event.Type.MERGE, targetBranch, newCommits));
            }
        }

        // Notify WebHooks if an event occurs
        private void notifyWebHooks(Event event) {
            for (WebHook webHook : webHooks) {
                if (webHook.branch().equals(event.branch()) && webHook.type() == event.type()) {
                    webHook.onEvent(event);
                }
            }
        }
    }

    // WebHook implementation
    private static class BranchWebHook implements WebHook {
        private final String branch;
        private final Event.Type eventType;
        private final List<Event> caughtEvents = new ArrayList<>();

        public BranchWebHook(String branch, Event.Type eventType) {
            this.branch = branch;
            this.eventType = eventType;
        }

        @Override
        public String branch() {
            return branch;
        }

        @Override
        public Event.Type type() {
            return eventType;
        }

        @Override
        public List<Event> caughtEvents() {
            return caughtEvents;
        }

        @Override
        public void onEvent(Event event) {
            caughtEvents.add(event); // Record the event when it's triggered
        }
    }
}