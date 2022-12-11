package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2022/day/11">Task description</a>
 */
public class Task11 {

    public int part1() throws IOException {
        return (int) calculateMonkeyBusiness(20, true);
    }

    public long part2() throws IOException {
        return calculateMonkeyBusiness(10000, false);
    }

    private long calculateMonkeyBusiness(int rounds, boolean divideBy3) throws IOException {
        List<Monkey> monkeys = new ArrayList<>(8);
        for (String unparsedMonkey : Files.readString(InputScanner.getPath(getClass().getSimpleName())).split("\n\n")) {
            List<String> lines = unparsedMonkey.lines().toList();
            List<Long> items = Arrays.stream(lines.get(1).replaceAll("\\D+", " ").trim().split(" "))
                    .mapToLong(Integer::parseInt).boxed().collect(Collectors.toList());
            Function<Long, Long> operation = parseOperation(lines.get(2).substring("  Operation: new = old ".length()).split(" "));
            int dividable = extractNumber(lines.get(3));
            int ifTrue = extractNumber(lines.get(4));
            int ifFalse = extractNumber(lines.get(5));
            monkeys.add(new Monkey(items, operation, dividable, ifTrue, ifFalse, divideBy3));
        }

        for (int i = 1; i <= rounds; i++) {
            for (Monkey monkey : monkeys) {
                var results = monkey.makeRound();
                monkeys.get(monkey.ifTrue).items.addAll(results.getKey());
                monkeys.get(monkey.ifFalse).items.addAll(results.getValue());
            }
        }

        //get most active monkey
        return monkeys.stream().sorted(Comparator.reverseOrder()).limit(2)
                .mapToLong(monkey -> monkey.inspects).reduce((int1, int2) -> int1 * int2).orElseThrow();
    }

    private int extractNumber(String line) {
        String[] split = line.split(" ");
        return Integer.parseInt(split[split.length-1]);
    }

    private static Function<Long, Long> parseOperation(String[] parts) {
        boolean isOld = "old".equals(parts[parts.length - 1]);
        int v = isOld ? -1 : Integer.parseInt(parts[parts.length - 1]);
        if (parts[0].equals("+")) return isOld ? a -> a+=a : a -> a+=v;
        else if (parts[0].equals("*")) return isOld ? a -> a*a : a -> a*=v;
        else throw new IllegalStateException();
    }

    private static class Monkey implements Comparable<Monkey> {
        final List<Long> items;
        long inspects = 0;
        final Function<Long, Long> operation;
        final int dividable;
        final int ifTrue;
        final int ifFalse;
        final boolean divideBy3;

        public Monkey(List<Long> items, Function<Long, Long> operation, int dividable, int ifTrue, int ifFalse, boolean divideBy3) {
            this.items = items;
            this.operation = operation;
            this.dividable = dividable;
            this.ifTrue = ifTrue;
            this.ifFalse = ifFalse;
            this.divideBy3 = divideBy3;
        }

        @Override
        public int compareTo(Monkey o) {
            return Long.compare(inspects, o.inspects);
        }

        public AbstractMap.SimpleImmutableEntry<List<Long>, List<Long>> makeRound() {
            inspects += items.size();
            List<Long> toMonkey1 = new ArrayList<>();
            List<Long> toMonkey2 = new ArrayList<>();
            for (Long item : items) {
                Long changedItem = operation.apply(item);
                if (divideBy3) changedItem /= 3;

                if (changedItem % dividable == 0) toMonkey1.add(changedItem);
                else toMonkey2.add(changedItem);
            }
            items.clear();
            return new AbstractMap.SimpleImmutableEntry<>(toMonkey1, toMonkey2);
        }

        @Override
        public String toString() {
            return String.valueOf(inspects);
        }
    }
}
