package com.boundlessgeo.spatialconnect.schema;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SCMessage.proto

public final class SCMessageOuterClass {
  private SCMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SCMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SCMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 correlationId = 1;</code>
     */
    int getCorrelationId();

    /**
     * <code>optional string replyTo = 2;</code>
     */
    java.lang.String getReplyTo();
    /**
     * <code>optional string replyTo = 2;</code>
     */
    com.google.protobuf.ByteString
        getReplyToBytes();

    /**
     * <code>optional int32 action = 3;</code>
     */
    int getAction();

    /**
     * <code>optional string payload = 4;</code>
     */
    java.lang.String getPayload();
    /**
     * <code>optional string payload = 4;</code>
     */
    com.google.protobuf.ByteString
        getPayloadBytes();
  }
  /**
   * Protobuf type {@code SCMessage}
   */
  public  static final class SCMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SCMessage)
      SCMessageOrBuilder {
    // Use SCMessage.newBuilder() to construct.
    private SCMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SCMessage() {
      correlationId_ = 0;
      replyTo_ = "";
      action_ = 0;
      payload_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private SCMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              correlationId_ = input.readInt32();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              replyTo_ = s;
              break;
            }
            case 24: {

              action_ = input.readInt32();
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              payload_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SCMessageOuterClass.internal_static_SCMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SCMessageOuterClass.internal_static_SCMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SCMessageOuterClass.SCMessage.class, SCMessageOuterClass.SCMessage.Builder.class);
    }

    public static final int CORRELATIONID_FIELD_NUMBER = 1;
    private int correlationId_;
    /**
     * <code>optional int32 correlationId = 1;</code>
     */
    public int getCorrelationId() {
      return correlationId_;
    }

    public static final int REPLYTO_FIELD_NUMBER = 2;
    private volatile java.lang.Object replyTo_;
    /**
     * <code>optional string replyTo = 2;</code>
     */
    public java.lang.String getReplyTo() {
      java.lang.Object ref = replyTo_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        replyTo_ = s;
        return s;
      }
    }
    /**
     * <code>optional string replyTo = 2;</code>
     */
    public com.google.protobuf.ByteString
        getReplyToBytes() {
      java.lang.Object ref = replyTo_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        replyTo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ACTION_FIELD_NUMBER = 3;
    private int action_;
    /**
     * <code>optional int32 action = 3;</code>
     */
    public int getAction() {
      return action_;
    }

    public static final int PAYLOAD_FIELD_NUMBER = 4;
    private volatile java.lang.Object payload_;
    /**
     * <code>optional string payload = 4;</code>
     */
    public java.lang.String getPayload() {
      java.lang.Object ref = payload_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        payload_ = s;
        return s;
      }
    }
    /**
     * <code>optional string payload = 4;</code>
     */
    public com.google.protobuf.ByteString
        getPayloadBytes() {
      java.lang.Object ref = payload_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        payload_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (correlationId_ != 0) {
        output.writeInt32(1, correlationId_);
      }
      if (!getReplyToBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, replyTo_);
      }
      if (action_ != 0) {
        output.writeInt32(3, action_);
      }
      if (!getPayloadBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, payload_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (correlationId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, correlationId_);
      }
      if (!getReplyToBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, replyTo_);
      }
      if (action_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, action_);
      }
      if (!getPayloadBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, payload_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof SCMessageOuterClass.SCMessage)) {
        return super.equals(obj);
      }
      SCMessageOuterClass.SCMessage other = (SCMessageOuterClass.SCMessage) obj;

      boolean result = true;
      result = result && (getCorrelationId()
          == other.getCorrelationId());
      result = result && getReplyTo()
          .equals(other.getReplyTo());
      result = result && (getAction()
          == other.getAction());
      result = result && getPayload()
          .equals(other.getPayload());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + CORRELATIONID_FIELD_NUMBER;
      hash = (53 * hash) + getCorrelationId();
      hash = (37 * hash) + REPLYTO_FIELD_NUMBER;
      hash = (53 * hash) + getReplyTo().hashCode();
      hash = (37 * hash) + ACTION_FIELD_NUMBER;
      hash = (53 * hash) + getAction();
      hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
      hash = (53 * hash) + getPayload().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static SCMessageOuterClass.SCMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static SCMessageOuterClass.SCMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static SCMessageOuterClass.SCMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SCMessageOuterClass.SCMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(SCMessageOuterClass.SCMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SCMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SCMessage)
        SCMessageOuterClass.SCMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return SCMessageOuterClass.internal_static_SCMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return SCMessageOuterClass.internal_static_SCMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                SCMessageOuterClass.SCMessage.class, SCMessageOuterClass.SCMessage.Builder.class);
      }

      // Construct using SCMessageOuterClass.SCMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        correlationId_ = 0;

        replyTo_ = "";

        action_ = 0;

        payload_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return SCMessageOuterClass.internal_static_SCMessage_descriptor;
      }

      public SCMessageOuterClass.SCMessage getDefaultInstanceForType() {
        return SCMessageOuterClass.SCMessage.getDefaultInstance();
      }

      public SCMessageOuterClass.SCMessage build() {
        SCMessageOuterClass.SCMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public SCMessageOuterClass.SCMessage buildPartial() {
        SCMessageOuterClass.SCMessage result = new SCMessageOuterClass.SCMessage(this);
        result.correlationId_ = correlationId_;
        result.replyTo_ = replyTo_;
        result.action_ = action_;
        result.payload_ = payload_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof SCMessageOuterClass.SCMessage) {
          return mergeFrom((SCMessageOuterClass.SCMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(SCMessageOuterClass.SCMessage other) {
        if (other == SCMessageOuterClass.SCMessage.getDefaultInstance()) return this;
        if (other.getCorrelationId() != 0) {
          setCorrelationId(other.getCorrelationId());
        }
        if (!other.getReplyTo().isEmpty()) {
          replyTo_ = other.replyTo_;
          onChanged();
        }
        if (other.getAction() != 0) {
          setAction(other.getAction());
        }
        if (!other.getPayload().isEmpty()) {
          payload_ = other.payload_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        SCMessageOuterClass.SCMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (SCMessageOuterClass.SCMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int correlationId_ ;
      /**
       * <code>optional int32 correlationId = 1;</code>
       */
      public int getCorrelationId() {
        return correlationId_;
      }
      /**
       * <code>optional int32 correlationId = 1;</code>
       */
      public Builder setCorrelationId(int value) {
        
        correlationId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 correlationId = 1;</code>
       */
      public Builder clearCorrelationId() {
        
        correlationId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object replyTo_ = "";
      /**
       * <code>optional string replyTo = 2;</code>
       */
      public java.lang.String getReplyTo() {
        java.lang.Object ref = replyTo_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          replyTo_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string replyTo = 2;</code>
       */
      public com.google.protobuf.ByteString
          getReplyToBytes() {
        java.lang.Object ref = replyTo_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          replyTo_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string replyTo = 2;</code>
       */
      public Builder setReplyTo(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        replyTo_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string replyTo = 2;</code>
       */
      public Builder clearReplyTo() {
        
        replyTo_ = getDefaultInstance().getReplyTo();
        onChanged();
        return this;
      }
      /**
       * <code>optional string replyTo = 2;</code>
       */
      public Builder setReplyToBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        replyTo_ = value;
        onChanged();
        return this;
      }

      private int action_ ;
      /**
       * <code>optional int32 action = 3;</code>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>optional int32 action = 3;</code>
       */
      public Builder setAction(int value) {
        
        action_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 action = 3;</code>
       */
      public Builder clearAction() {
        
        action_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object payload_ = "";
      /**
       * <code>optional string payload = 4;</code>
       */
      public java.lang.String getPayload() {
        java.lang.Object ref = payload_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          payload_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string payload = 4;</code>
       */
      public com.google.protobuf.ByteString
          getPayloadBytes() {
        java.lang.Object ref = payload_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          payload_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string payload = 4;</code>
       */
      public Builder setPayload(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        payload_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string payload = 4;</code>
       */
      public Builder clearPayload() {
        
        payload_ = getDefaultInstance().getPayload();
        onChanged();
        return this;
      }
      /**
       * <code>optional string payload = 4;</code>
       */
      public Builder setPayloadBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        payload_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:SCMessage)
    }

    // @@protoc_insertion_point(class_scope:SCMessage)
    private static final SCMessageOuterClass.SCMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new SCMessageOuterClass.SCMessage();
    }

    public static SCMessageOuterClass.SCMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SCMessage>
        PARSER = new com.google.protobuf.AbstractParser<SCMessage>() {
      public SCMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new SCMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SCMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SCMessage> getParserForType() {
      return PARSER;
    }

    public SCMessageOuterClass.SCMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SCMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SCMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017SCMessage.proto\"T\n\tSCMessage\022\025\n\rcorrel" +
      "ationId\030\001 \001(\005\022\017\n\007replyTo\030\002 \001(\t\022\016\n\006action" +
      "\030\003 \001(\005\022\017\n\007payload\030\004 \001(\tb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_SCMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SCMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SCMessage_descriptor,
        new java.lang.String[] { "CorrelationId", "ReplyTo", "Action", "Payload", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
