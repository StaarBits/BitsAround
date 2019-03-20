package org.staarbits.io.yaml;

/*
 * Copyright (c) 2019. StaarBits Network & Development says that this file is under the StaarBits Global Copyright (SGC).
 * Every file which contains this annotation as one of the first things written is under the SGC protocol.
 * The SGC (StaarBits Global Copyright) demonstrates that the file which has it cannot be copied and pasted as
 * an annotation file by anyone else who has not gotten the Owner rank at StaarBits. So... The most powerful rank
 * at the executive can spread this file. If someone uses this file without the permission given by the executive
 * administration, this same person will be able to be sued by the SEA (StaarBits Executive Administration); if
 * someone who works at StaarBits spreads this file, this person will as sooner as possible be removed from our
 * team and (s)he will also be able to response a lawsuit as well.
 */

/**
 * Various settings for controlling the input and output of a {@link Configuration}
 * @author Bukkit <https://github.com/Bukkit/Bukkit/tree/master/src/main/java/org/bukkit/configuration/ConfigurationOptions.java>
 */
public class ConfigurationOptions {
    private char pathSeparator = '.';
    private boolean copyDefaults = false;
    private final Configuration configuration;

    protected ConfigurationOptions(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns the {@link Configuration} that this object is responsible for.
     *
     * @return Parent configuration
     */
    public Configuration configuration() {
        return configuration;
    }

    /**
     * Gets the char that will be used to separate {@link
     * ConfigurationSection}s
     * <p>
     * This value does not affect how the {@link Configuration} is stored,
     * only in how you access the data. The default value is '.'.
     *
     * @return Path separator
     */
    public char pathSeparator() {
        return pathSeparator;
    }

    /**
     * Sets the char that will be used to separate {@link
     * ConfigurationSection}s
     * <p>
     * This value does not affect how the {@link Configuration} is stored,
     * only in how you access the data. The default value is '.'.
     *
     * @param value Path separator
     * @return This object, for chaining
     */
    public ConfigurationOptions pathSeparator(char value) {
        this.pathSeparator = value;
        return this;
    }

    /**
     * Checks if the {@link Configuration} should copy values from its default
     * {@link Configuration} directly.
     * <p>
     * If this is true, all values in the default Configuration will be
     * directly copied, making it impossible to distinguish between values
     * that were set and values that are provided by default. As a result,
     * {@link ConfigurationSection#contains(java.lang.String)} will always
     * return the same value as {@link
     * ConfigurationSection#isSet(java.lang.String)}. The default value is
     * false.
     *
     * @return Whether or not defaults are directly copied
     */
    public boolean copyDefaults() {
        return copyDefaults;
    }

    /**
     * Sets if the {@link Configuration} should copy values from its default
     * {@link Configuration} directly.
     * <p>
     * If this is true, all values in the default Configuration will be
     * directly copied, making it impossible to distinguish between values
     * that were set and values that are provided by default. As a result,
     * {@link ConfigurationSection#contains(java.lang.String)} will always
     * return the same value as {@link
     * ConfigurationSection#isSet(java.lang.String)}. The default value is
     * false.
     *
     * @param value Whether or not defaults are directly copied
     * @return This object, for chaining
     */
    public ConfigurationOptions copyDefaults(boolean value) {
        this.copyDefaults = value;
        return this;
    }
}
