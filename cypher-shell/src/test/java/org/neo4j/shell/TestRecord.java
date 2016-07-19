package org.neo4j.shell;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.util.Function;
import org.neo4j.driver.v1.util.Pair;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class TestRecord implements Record {

    final TreeMap<String, Value> valueMap = new TreeMap<>();

    public TestRecord() {
    }

    @Override
    public List<String> keys() {
        return valueMap.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public List<Value> values() {
        return valueMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean containsKey(String key) {
        return valueMap.containsKey(key);
    }

    @Override
    public int index(String key) {
        return keys().indexOf(key);
    }

    @Override
    public Value get(String key) {
        return valueMap.get(key);
    }

    @Override
    public Value get(int index) {
        return valueMap.get(keys().get(index));
    }

    @Override
    public int size() {
        return valueMap.size();
    }

    @Override
    public Map<String, Object> asMap() {
        throw new Util.NotImplementedYetException("Not implemented as no test has required it yet");
    }

    @Override
    public <T> Map<String, T> asMap(Function<Value, T> mapper) {
        throw new Util.NotImplementedYetException("Not implemented as no test has required it yet");
    }

    @Override
    public List<Pair<String, Value>> fields() {
        throw new Util.NotImplementedYetException("Not implemented as no test has required it yet");
    }

    public static TestRecord of(@Nonnull String key, @Nonnull String value) {
        return of(key, new TestValue() {
            @Override
            public Object asObject() {
                return value;
            }

            @Override
            public String asString() {
                return value;
            }
        });
    }

    public static TestRecord of(@Nonnull String key, @Nonnull Value value) {
        TestRecord record = new TestRecord();
        record.valueMap.put(key, value);

        return record;
    }
}
