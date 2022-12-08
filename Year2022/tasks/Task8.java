package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2022/day/8">Task description</a>
 */
public class Task8 {
    private final List<List<Integer>> trees;

    public Task8() throws IOException {
        List<String> lines = Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()));
        trees = lines.stream().map(s -> Arrays.stream(s.split("")).mapToInt(Integer::parseInt).boxed().toList()).toList();
    }

    public int part1() {
        int visibleTreeCounter = trees.get(0).size() + trees.get(trees.size() - 1).size();

        for (int i = 1; i+1 < trees.size(); i++) {
            List<Integer> treeRow = trees.get(i);
            visibleTreeCounter += 2; //2 outer trees
            for (int j = 1; j+1 < treeRow.size(); j++) if (isTreeVisible(j, treeRow, i)) visibleTreeCounter++;
        }

        return visibleTreeCounter;
    }

    public int part2() {
        int maxTreeScore = 0;
        for (int i = 0; i < trees.size(); i++) {
            List<Integer> treeRow = trees.get(i);
            for (int j = 0; j < treeRow.size(); j++) {
                int treeScore = getTreeScore(j, treeRow, i);
                if (treeScore > maxTreeScore) maxTreeScore = treeScore;
            }
        }
        return maxTreeScore;
    }

    private boolean isTreeVisible(int treeIndex, List<Integer> treeRow, int treeRowIndex) {
        int tree = treeRow.get(treeIndex);

        List<Integer> left = treeRow.subList(0, treeIndex);
        List<Integer> right = treeRow.subList(treeIndex+1, treeRow.size());
        List<List<Integer>> top = trees.subList(0, treeRowIndex);
        List<List<Integer>> bottom = trees.subList(treeRowIndex+1, trees.size());

        return bottom.stream().allMatch(integers -> integers.get(treeIndex) < tree)
                || top.stream().allMatch(integers -> integers.get(treeIndex) < tree)
                || left.stream().allMatch(integer -> integer < tree)
                ||right.stream().allMatch(integer -> integer < tree);
    }

    private int getTreeScore(int treeIndex, List<Integer> treeRow, int treeRowIndex) {
        int tree = treeRow.get(treeIndex);
        List<Integer> left = new ArrayList<>(treeRow.subList(0, treeIndex));
        List<Integer> right = treeRow.subList(treeIndex+1, treeRow.size());
        List<Integer> top = trees.subList(0, treeRowIndex).stream().map(integers -> integers.get(treeIndex)).collect(Collectors.toList());
        List<Integer> bottom = trees.subList(treeRowIndex + 1, trees.size()).stream().map(integers -> integers.get(treeIndex)).toList();
        Collections.reverse(left);
        Collections.reverse(top);

        return getAmountOfSeenTrees(tree, left)
                * getAmountOfSeenTrees(tree, right)
                * getAmountOfSeenTrees(tree, bottom)
                * getAmountOfSeenTrees(tree, top);
    }

    private int getAmountOfSeenTrees(int tree, List<Integer> integers) {
        int seeTreesCounter = 0;
        for (int integer : integers) {
            seeTreesCounter++;
            if (integer >= tree) break;
        }
        return seeTreesCounter;
    }
}
