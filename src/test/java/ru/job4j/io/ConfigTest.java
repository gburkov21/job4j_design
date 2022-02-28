package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoPropertyKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenEmptyConfigFile() {
        String path = "./data/empty_file.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.size(), is(0));
    }
}