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

import java.util.Map;

/**
 * Represents an object that may be serialized.
 * <p>
 * These objects MUST implement one of the following, in addition to the
 * methods as defined by this interface:
 * <ul>
 * <li>A static method "deserialize" that accepts a single {@link Map}&lt;
 * {@link String}, {@link Object}> and returns the class.</li>
 * <li>A static method "valueOf" that accepts a single {@link Map}&lt;{@link
 * String}, {@link Object}> and returns the class.</li>
 * <li>A constructor that accepts a single {@link Map}&lt;{@link String},
 * {@link Object}>.</li>
 * </ul>
 * In addition to implementing this interface, you must register the class
 * with {@link ConfigurationSerialization#registerClass(Class)}.
 *
 * @author Bukkit <https://github.com/Bukkit/Bukkit/tree/master/src/main/java/org/bukkit/configuration/serialization/ConfigurationSerializable.java>
 * @see DelegateDeserialization
 * @see SerializableAs
 */
public interface ConfigurationSerializable {

    /**
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    public Map<String, Object> serialize();
}
