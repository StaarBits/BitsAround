package org.staarbits.io;

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

import com.staarbits.core.DataAs;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Serializable is responsible in demonstrating what class / interface is serializable.
 * <p>Serializability of a class is enabled by the class implementing the <code>java.io.Serializable</code> or -- now --
 * the <code>org.staarbits.io.Serializable</code> interfaces. Classes that do not implement these interfaces will not have
 * any of their state serialized or deserialized. All subtypes of a serialiazable <code><strong><b>class</b></strong></code>
 * are themselves serializable. The serializable interface has no methods or fields and serves only identify the semantics
 * of being serializable. <p>
 * To allow subtypes of non-serializable classes to be serialized, the subtype may assume responsibility for saving and
 * restoring the state of the supertype's <code><strong><b>public</b></strong></code>, <code><strong><b>protected</b></strong></code>,
 * (if accessible) <code><strong>package</strong></code> fields. It i an error t odeclare a class Serializable if this is
 * not the case. The error will be detected at <code>{@link java.lang.annotation.RetentionPolicy#RUNTIME runtime}</code>.
 * <p>
 * During serialization, the fields of non-serializable classes will be initialized using the <code><strong>public</strong></code>
 * or <code><strong>protected</strong></code> no-argument constructor of the class. A no-argument constructor must be accessible
 * to the subclass that is serializable. The fields of serializable subclasses will be restored from the stream. <p>
 * When traversing a graph, an object may be encountered that does not suport the <code>Serializable</code> interface. In
 * this case the <code>{@link java.io.NotSerializableException}</code> will be thrown and will identify the class of the
 * non-serializable object. <p>
 * Classes that require special handling during the serialization and deserialization process must implement special methods
 * with these exact signatures:
 * <pre>
 *   private void writeObject({@link java.io.ObjectOutputStream} out) throws <code>{@link java.io.IOException}</code>;
 *   private void readObject({@link java.io.ObjectInputStream} in) throws <code>{@link java.io.IOException}</code>;
 *   private void readObjectNoData() throws {@link java.io.ObjectStreamException};
 * </pre>
 * <p>
 * The <code>writeObject</code> method is responsible for writing the state of the object for its particular class so that
 * the corresponding the Object's field can be invoked by calling <code>{@link ObjectOutputStream#defaultWriteObject()
 * out.defaultWriteObject()}</code>. The method does not need to concern itself with the state belonging to its superclasses
 * or subclasses. State is saved by writing the individual fields to the <code>ObjectOutputStream</code> using the writeObject
 * method or by using the methods for primitive data types supported by <code>{@link java.io.DataOutput}</code>. <p
 * The <code>readObject</code> methods is responsible for reading from the stream and restoring the classes fields. It may
 * call <code>{@link ObjectInputStream#defaultReadObject() in.defaultReadObject()}</code> method uses information in the
 * stream to assign the fields of the object saved in the stream with the correspondingly named fields in the current object.
 * This handles the case when the class has evolved to add new fields. The method does not need to concern itself with the
 * state belonging to its superclasses and subclasses. State is saved by writing the individual fields to the ObjectOutputStream
 * using the <code>writeObject</code> method or by using the methods for primitive data types supported by
 * <code>{@link java.io.DataOutput}</code>. <p>
 * The <code>readObjectNoData</code> method is responsible for initializing the state of the object for its particular class
 * in the event that serialization stream does not list the given class as a superclass of the object being deserialized.
 * This may occur in cases where the receiving party uses a party, and the receiver's version extends classes that are not
 * extended by been tampered; hence, <code>readObjectNoData</code> is useful for initializing deserialized objects properly
 * despite a "hostile" or incomplete source stream. <p>
 * Serializable classes that need to designate an alternative object to be used when writing an object to the stream should
 * implement this special method with the exact signature:
 * <pre>
 *   ANY-ACCESS-MODIFIER Object <code>writeReplace() throws {@link java.io.IOException};</code>
 * </pre>
 * <p>
 * This <code>writeReplace</code> method is invoked by serialization if the method exists and it would be accessible from a
 * method defined within the class of the object being serialized. Thus, the method can have <code><strong>private</strong></code>,
 * <code><strong>protected</strong></code> and <code>package-private</code> access. Subclass access to this method follows java
 * accessibility rules. <p>
 * Classes that need to designate a replacement when an instance of it is read from the stream should implement this special
 * method with the exact signature.
 * <pre>
 *   ANY-ACCESS-MODIFIER Object <code>readResolve() throws {@link java.io.IOException};</code>
 * </pre>
 * <p>
 * This readResolve method follows the same invocation rules and accessibility rules as writeReplace. <p>
 * The serialization runtime associates with each serializable class a version number, called a <code>serialVersionUID</code>,
 * which is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for
 * that object that are compatible with respect to serialization. If the receiver has loaded a class for the object that has
 * a different <code>serialVersionUID</code> than that of the corresponding sender's class, then deserialization will result
 * in an <code>{@link java.io.InvalidClassException}</code>. A serializable class can declare its own serialVersionUID explicitly
 * by declaring a field named <code>"serialVersionUID"</code> that must be <code><strong><b>static</b></strong></code>,
 * <code><strong><b>final</b></strong></code>, and of type <code><strong><b>long</b></strong></code>:
 * <pre>
 *   ANY-ACCESS-MODIFIER <strong><b>static final long</b></strong> serialVersion <code><strong>42L</strong></code>;
 * </pre>
 * <p>
 * If a serializable class does not explicitly declare a <code>serialVersionUID</code>, then the serialization runtime will
 * calculate a default serialVersionUID value for that class based on various aspects of the class, as described in the Java (TM)
 * Object Serialization Specification. However, it is <em>strongly recommended</em> that all serializable classes explicitly
 * declare serialVersionUID values, since the default serialVersionUID computation is highly sensitive to class details that
 * may vary depending on compiler implementations, and can thus result in unexpected <code>InvalidClassException</code> during
 * deserialization. Therefore, to guarantee a consistent serialVersionUID value across different java compiler implementations,
 * a serializable class must declare an explicit serialVersionUID declarations use the <code><strong>private</strong></code>
 * modifier where possible, since such declarations apply only the immediately declaring class-serialVersionUID fields are not
 * useful as inherited members. Array the default computed value, but the requirement for matching serialVersionUID, so they
 * always have serialVersionUID values is waived for array classes.
 */
@DataAs("Serializable")
public interface Serializable extends java.io.Serializable
{
  
  /*
   * The Serializable is an interface which works simply extending the java.io.Serializable.
   */
}
