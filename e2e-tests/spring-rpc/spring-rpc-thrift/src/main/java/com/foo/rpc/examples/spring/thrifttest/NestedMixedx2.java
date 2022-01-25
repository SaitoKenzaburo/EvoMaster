/**
 * Autogenerated by Thrift Compiler (0.15.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.foo.rpc.examples.spring.thrifttest;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.15.0)", date = "2021-12-08")
public class NestedMixedx2 implements org.apache.thrift.TBase<NestedMixedx2, NestedMixedx2._Fields>, java.io.Serializable, Cloneable, Comparable<NestedMixedx2> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("NestedMixedx2");

  private static final org.apache.thrift.protocol.TField INT_SET_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("int_set_list", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField MAP_INT_STRSET_FIELD_DESC = new org.apache.thrift.protocol.TField("map_int_strset", org.apache.thrift.protocol.TType.MAP, (short)2);
  private static final org.apache.thrift.protocol.TField MAP_INT_STRSET_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("map_int_strset_list", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new NestedMixedx2StandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new NestedMixedx2TupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<java.util.Set<java.lang.Integer>> int_set_list; // required
  public @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> map_int_strset; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> map_int_strset_list; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INT_SET_LIST((short)1, "int_set_list"),
    MAP_INT_STRSET((short)2, "map_int_strset"),
    MAP_INT_STRSET_LIST((short)3, "map_int_strset_list");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // INT_SET_LIST
          return INT_SET_LIST;
        case 2: // MAP_INT_STRSET
          return MAP_INT_STRSET;
        case 3: // MAP_INT_STRSET_LIST
          return MAP_INT_STRSET_LIST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INT_SET_LIST, new org.apache.thrift.meta_data.FieldMetaData("int_set_list", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)))));
    tmpMap.put(_Fields.MAP_INT_STRSET, new org.apache.thrift.meta_data.FieldMetaData("map_int_strset", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)))));
    tmpMap.put(_Fields.MAP_INT_STRSET_LIST, new org.apache.thrift.meta_data.FieldMetaData("map_int_strset_list", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
                new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
                    new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(NestedMixedx2.class, metaDataMap);
  }

  public NestedMixedx2() {
  }

  public NestedMixedx2(
    java.util.List<java.util.Set<java.lang.Integer>> int_set_list,
    java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> map_int_strset,
    java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> map_int_strset_list)
  {
    this();
    this.int_set_list = int_set_list;
    this.map_int_strset = map_int_strset;
    this.map_int_strset_list = map_int_strset_list;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public NestedMixedx2(NestedMixedx2 other) {
    if (other.isSetInt_set_list()) {
      java.util.List<java.util.Set<java.lang.Integer>> __this__int_set_list = new java.util.ArrayList<java.util.Set<java.lang.Integer>>(other.int_set_list.size());
      for (java.util.Set<java.lang.Integer> other_element : other.int_set_list) {
        java.util.Set<java.lang.Integer> __this__int_set_list_copy = new java.util.HashSet<java.lang.Integer>(other_element);
        __this__int_set_list.add(__this__int_set_list_copy);
      }
      this.int_set_list = __this__int_set_list;
    }
    if (other.isSetMap_int_strset()) {
      java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> __this__map_int_strset = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(other.map_int_strset.size());
      for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> other_element : other.map_int_strset.entrySet()) {

        java.lang.Integer other_element_key = other_element.getKey();
        java.util.Set<java.lang.String> other_element_value = other_element.getValue();

        java.lang.Integer __this__map_int_strset_copy_key = other_element_key;

        java.util.Set<java.lang.String> __this__map_int_strset_copy_value = new java.util.HashSet<java.lang.String>(other_element_value);

        __this__map_int_strset.put(__this__map_int_strset_copy_key, __this__map_int_strset_copy_value);
      }
      this.map_int_strset = __this__map_int_strset;
    }
    if (other.isSetMap_int_strset_list()) {
      java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> __this__map_int_strset_list = new java.util.ArrayList<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>>(other.map_int_strset_list.size());
      for (java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> other_element : other.map_int_strset_list) {
        java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> __this__map_int_strset_list_copy = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(other_element.size());
        for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> other_element_element : other_element.entrySet()) {

          java.lang.Integer other_element_element_key = other_element_element.getKey();
          java.util.Set<java.lang.String> other_element_element_value = other_element_element.getValue();

          java.lang.Integer __this__map_int_strset_list_copy_copy_key = other_element_element_key;

          java.util.Set<java.lang.String> __this__map_int_strset_list_copy_copy_value = new java.util.HashSet<java.lang.String>(other_element_element_value);

          __this__map_int_strset_list_copy.put(__this__map_int_strset_list_copy_copy_key, __this__map_int_strset_list_copy_copy_value);
        }
        __this__map_int_strset_list.add(__this__map_int_strset_list_copy);
      }
      this.map_int_strset_list = __this__map_int_strset_list;
    }
  }

  public NestedMixedx2 deepCopy() {
    return new NestedMixedx2(this);
  }

  @Override
  public void clear() {
    this.int_set_list = null;
    this.map_int_strset = null;
    this.map_int_strset_list = null;
  }

  public int getInt_set_listSize() {
    return (this.int_set_list == null) ? 0 : this.int_set_list.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.util.Set<java.lang.Integer>> getInt_set_listIterator() {
    return (this.int_set_list == null) ? null : this.int_set_list.iterator();
  }

  public void addToInt_set_list(java.util.Set<java.lang.Integer> elem) {
    if (this.int_set_list == null) {
      this.int_set_list = new java.util.ArrayList<java.util.Set<java.lang.Integer>>();
    }
    this.int_set_list.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.util.Set<java.lang.Integer>> getInt_set_list() {
    return this.int_set_list;
  }

  public NestedMixedx2 setInt_set_list(@org.apache.thrift.annotation.Nullable java.util.List<java.util.Set<java.lang.Integer>> int_set_list) {
    this.int_set_list = int_set_list;
    return this;
  }

  public void unsetInt_set_list() {
    this.int_set_list = null;
  }

  /** Returns true if field int_set_list is set (has been assigned a value) and false otherwise */
  public boolean isSetInt_set_list() {
    return this.int_set_list != null;
  }

  public void setInt_set_listIsSet(boolean value) {
    if (!value) {
      this.int_set_list = null;
    }
  }

  public int getMap_int_strsetSize() {
    return (this.map_int_strset == null) ? 0 : this.map_int_strset.size();
  }

  public void putToMap_int_strset(int key, java.util.Set<java.lang.String> val) {
    if (this.map_int_strset == null) {
      this.map_int_strset = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>();
    }
    this.map_int_strset.put(key, val);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> getMap_int_strset() {
    return this.map_int_strset;
  }

  public NestedMixedx2 setMap_int_strset(@org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> map_int_strset) {
    this.map_int_strset = map_int_strset;
    return this;
  }

  public void unsetMap_int_strset() {
    this.map_int_strset = null;
  }

  /** Returns true if field map_int_strset is set (has been assigned a value) and false otherwise */
  public boolean isSetMap_int_strset() {
    return this.map_int_strset != null;
  }

  public void setMap_int_strsetIsSet(boolean value) {
    if (!value) {
      this.map_int_strset = null;
    }
  }

  public int getMap_int_strset_listSize() {
    return (this.map_int_strset_list == null) ? 0 : this.map_int_strset_list.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> getMap_int_strset_listIterator() {
    return (this.map_int_strset_list == null) ? null : this.map_int_strset_list.iterator();
  }

  public void addToMap_int_strset_list(java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> elem) {
    if (this.map_int_strset_list == null) {
      this.map_int_strset_list = new java.util.ArrayList<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>>();
    }
    this.map_int_strset_list.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> getMap_int_strset_list() {
    return this.map_int_strset_list;
  }

  public NestedMixedx2 setMap_int_strset_list(@org.apache.thrift.annotation.Nullable java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>> map_int_strset_list) {
    this.map_int_strset_list = map_int_strset_list;
    return this;
  }

  public void unsetMap_int_strset_list() {
    this.map_int_strset_list = null;
  }

  /** Returns true if field map_int_strset_list is set (has been assigned a value) and false otherwise */
  public boolean isSetMap_int_strset_list() {
    return this.map_int_strset_list != null;
  }

  public void setMap_int_strset_listIsSet(boolean value) {
    if (!value) {
      this.map_int_strset_list = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case INT_SET_LIST:
      if (value == null) {
        unsetInt_set_list();
      } else {
        setInt_set_list((java.util.List<java.util.Set<java.lang.Integer>>)value);
      }
      break;

    case MAP_INT_STRSET:
      if (value == null) {
        unsetMap_int_strset();
      } else {
        setMap_int_strset((java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>)value);
      }
      break;

    case MAP_INT_STRSET_LIST:
      if (value == null) {
        unsetMap_int_strset_list();
      } else {
        setMap_int_strset_list((java.util.List<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case INT_SET_LIST:
      return getInt_set_list();

    case MAP_INT_STRSET:
      return getMap_int_strset();

    case MAP_INT_STRSET_LIST:
      return getMap_int_strset_list();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case INT_SET_LIST:
      return isSetInt_set_list();
    case MAP_INT_STRSET:
      return isSetMap_int_strset();
    case MAP_INT_STRSET_LIST:
      return isSetMap_int_strset_list();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof NestedMixedx2)
      return this.equals((NestedMixedx2)that);
    return false;
  }

  public boolean equals(NestedMixedx2 that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_int_set_list = true && this.isSetInt_set_list();
    boolean that_present_int_set_list = true && that.isSetInt_set_list();
    if (this_present_int_set_list || that_present_int_set_list) {
      if (!(this_present_int_set_list && that_present_int_set_list))
        return false;
      if (!this.int_set_list.equals(that.int_set_list))
        return false;
    }

    boolean this_present_map_int_strset = true && this.isSetMap_int_strset();
    boolean that_present_map_int_strset = true && that.isSetMap_int_strset();
    if (this_present_map_int_strset || that_present_map_int_strset) {
      if (!(this_present_map_int_strset && that_present_map_int_strset))
        return false;
      if (!this.map_int_strset.equals(that.map_int_strset))
        return false;
    }

    boolean this_present_map_int_strset_list = true && this.isSetMap_int_strset_list();
    boolean that_present_map_int_strset_list = true && that.isSetMap_int_strset_list();
    if (this_present_map_int_strset_list || that_present_map_int_strset_list) {
      if (!(this_present_map_int_strset_list && that_present_map_int_strset_list))
        return false;
      if (!this.map_int_strset_list.equals(that.map_int_strset_list))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetInt_set_list()) ? 131071 : 524287);
    if (isSetInt_set_list())
      hashCode = hashCode * 8191 + int_set_list.hashCode();

    hashCode = hashCode * 8191 + ((isSetMap_int_strset()) ? 131071 : 524287);
    if (isSetMap_int_strset())
      hashCode = hashCode * 8191 + map_int_strset.hashCode();

    hashCode = hashCode * 8191 + ((isSetMap_int_strset_list()) ? 131071 : 524287);
    if (isSetMap_int_strset_list())
      hashCode = hashCode * 8191 + map_int_strset_list.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(NestedMixedx2 other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetInt_set_list(), other.isSetInt_set_list());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInt_set_list()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.int_set_list, other.int_set_list);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetMap_int_strset(), other.isSetMap_int_strset());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMap_int_strset()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.map_int_strset, other.map_int_strset);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetMap_int_strset_list(), other.isSetMap_int_strset_list());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMap_int_strset_list()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.map_int_strset_list, other.map_int_strset_list);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("NestedMixedx2(");
    boolean first = true;

    sb.append("int_set_list:");
    if (this.int_set_list == null) {
      sb.append("null");
    } else {
      sb.append(this.int_set_list);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("map_int_strset:");
    if (this.map_int_strset == null) {
      sb.append("null");
    } else {
      sb.append(this.map_int_strset);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("map_int_strset_list:");
    if (this.map_int_strset_list == null) {
      sb.append("null");
    } else {
      sb.append(this.map_int_strset_list);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class NestedMixedx2StandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NestedMixedx2StandardScheme getScheme() {
      return new NestedMixedx2StandardScheme();
    }
  }

  private static class NestedMixedx2StandardScheme extends org.apache.thrift.scheme.StandardScheme<NestedMixedx2> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, NestedMixedx2 struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INT_SET_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list206 = iprot.readListBegin();
                struct.int_set_list = new java.util.ArrayList<java.util.Set<java.lang.Integer>>(_list206.size);
                @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.Integer> _elem207;
                for (int _i208 = 0; _i208 < _list206.size; ++_i208)
                {
                  {
                    org.apache.thrift.protocol.TSet _set209 = iprot.readSetBegin();
                    _elem207 = new java.util.HashSet<java.lang.Integer>(2*_set209.size);
                    int _elem210;
                    for (int _i211 = 0; _i211 < _set209.size; ++_i211)
                    {
                      _elem210 = iprot.readI32();
                      _elem207.add(_elem210);
                    }
                    iprot.readSetEnd();
                  }
                  struct.int_set_list.add(_elem207);
                }
                iprot.readListEnd();
              }
              struct.setInt_set_listIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MAP_INT_STRSET
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map212 = iprot.readMapBegin();
                struct.map_int_strset = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(2*_map212.size);
                int _key213;
                @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> _val214;
                for (int _i215 = 0; _i215 < _map212.size; ++_i215)
                {
                  _key213 = iprot.readI32();
                  {
                    org.apache.thrift.protocol.TSet _set216 = iprot.readSetBegin();
                    _val214 = new java.util.HashSet<java.lang.String>(2*_set216.size);
                    @org.apache.thrift.annotation.Nullable java.lang.String _elem217;
                    for (int _i218 = 0; _i218 < _set216.size; ++_i218)
                    {
                      _elem217 = iprot.readString();
                      _val214.add(_elem217);
                    }
                    iprot.readSetEnd();
                  }
                  struct.map_int_strset.put(_key213, _val214);
                }
                iprot.readMapEnd();
              }
              struct.setMap_int_strsetIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MAP_INT_STRSET_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list219 = iprot.readListBegin();
                struct.map_int_strset_list = new java.util.ArrayList<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>>(_list219.size);
                @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> _elem220;
                for (int _i221 = 0; _i221 < _list219.size; ++_i221)
                {
                  {
                    org.apache.thrift.protocol.TMap _map222 = iprot.readMapBegin();
                    _elem220 = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(2*_map222.size);
                    int _key223;
                    @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> _val224;
                    for (int _i225 = 0; _i225 < _map222.size; ++_i225)
                    {
                      _key223 = iprot.readI32();
                      {
                        org.apache.thrift.protocol.TSet _set226 = iprot.readSetBegin();
                        _val224 = new java.util.HashSet<java.lang.String>(2*_set226.size);
                        @org.apache.thrift.annotation.Nullable java.lang.String _elem227;
                        for (int _i228 = 0; _i228 < _set226.size; ++_i228)
                        {
                          _elem227 = iprot.readString();
                          _val224.add(_elem227);
                        }
                        iprot.readSetEnd();
                      }
                      _elem220.put(_key223, _val224);
                    }
                    iprot.readMapEnd();
                  }
                  struct.map_int_strset_list.add(_elem220);
                }
                iprot.readListEnd();
              }
              struct.setMap_int_strset_listIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, NestedMixedx2 struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.int_set_list != null) {
        oprot.writeFieldBegin(INT_SET_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.SET, struct.int_set_list.size()));
          for (java.util.Set<java.lang.Integer> _iter229 : struct.int_set_list)
          {
            {
              oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, _iter229.size()));
              for (int _iter230 : _iter229)
              {
                oprot.writeI32(_iter230);
              }
              oprot.writeSetEnd();
            }
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.map_int_strset != null) {
        oprot.writeFieldBegin(MAP_INT_STRSET_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.SET, struct.map_int_strset.size()));
          for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> _iter231 : struct.map_int_strset.entrySet())
          {
            oprot.writeI32(_iter231.getKey());
            {
              oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, _iter231.getValue().size()));
              for (java.lang.String _iter232 : _iter231.getValue())
              {
                oprot.writeString(_iter232);
              }
              oprot.writeSetEnd();
            }
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.map_int_strset_list != null) {
        oprot.writeFieldBegin(MAP_INT_STRSET_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.MAP, struct.map_int_strset_list.size()));
          for (java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> _iter233 : struct.map_int_strset_list)
          {
            {
              oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.SET, _iter233.size()));
              for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> _iter234 : _iter233.entrySet())
              {
                oprot.writeI32(_iter234.getKey());
                {
                  oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, _iter234.getValue().size()));
                  for (java.lang.String _iter235 : _iter234.getValue())
                  {
                    oprot.writeString(_iter235);
                  }
                  oprot.writeSetEnd();
                }
              }
              oprot.writeMapEnd();
            }
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class NestedMixedx2TupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public NestedMixedx2TupleScheme getScheme() {
      return new NestedMixedx2TupleScheme();
    }
  }

  private static class NestedMixedx2TupleScheme extends org.apache.thrift.scheme.TupleScheme<NestedMixedx2> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, NestedMixedx2 struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetInt_set_list()) {
        optionals.set(0);
      }
      if (struct.isSetMap_int_strset()) {
        optionals.set(1);
      }
      if (struct.isSetMap_int_strset_list()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetInt_set_list()) {
        {
          oprot.writeI32(struct.int_set_list.size());
          for (java.util.Set<java.lang.Integer> _iter236 : struct.int_set_list)
          {
            {
              oprot.writeI32(_iter236.size());
              for (int _iter237 : _iter236)
              {
                oprot.writeI32(_iter237);
              }
            }
          }
        }
      }
      if (struct.isSetMap_int_strset()) {
        {
          oprot.writeI32(struct.map_int_strset.size());
          for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> _iter238 : struct.map_int_strset.entrySet())
          {
            oprot.writeI32(_iter238.getKey());
            {
              oprot.writeI32(_iter238.getValue().size());
              for (java.lang.String _iter239 : _iter238.getValue())
              {
                oprot.writeString(_iter239);
              }
            }
          }
        }
      }
      if (struct.isSetMap_int_strset_list()) {
        {
          oprot.writeI32(struct.map_int_strset_list.size());
          for (java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> _iter240 : struct.map_int_strset_list)
          {
            {
              oprot.writeI32(_iter240.size());
              for (java.util.Map.Entry<java.lang.Integer, java.util.Set<java.lang.String>> _iter241 : _iter240.entrySet())
              {
                oprot.writeI32(_iter241.getKey());
                {
                  oprot.writeI32(_iter241.getValue().size());
                  for (java.lang.String _iter242 : _iter241.getValue())
                  {
                    oprot.writeString(_iter242);
                  }
                }
              }
            }
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, NestedMixedx2 struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list243 = iprot.readListBegin(org.apache.thrift.protocol.TType.SET);
          struct.int_set_list = new java.util.ArrayList<java.util.Set<java.lang.Integer>>(_list243.size);
          @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.Integer> _elem244;
          for (int _i245 = 0; _i245 < _list243.size; ++_i245)
          {
            {
              org.apache.thrift.protocol.TSet _set246 = iprot.readSetBegin(org.apache.thrift.protocol.TType.I32);
              _elem244 = new java.util.HashSet<java.lang.Integer>(2*_set246.size);
              int _elem247;
              for (int _i248 = 0; _i248 < _set246.size; ++_i248)
              {
                _elem247 = iprot.readI32();
                _elem244.add(_elem247);
              }
            }
            struct.int_set_list.add(_elem244);
          }
        }
        struct.setInt_set_listIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map249 = iprot.readMapBegin(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.SET); 
          struct.map_int_strset = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(2*_map249.size);
          int _key250;
          @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> _val251;
          for (int _i252 = 0; _i252 < _map249.size; ++_i252)
          {
            _key250 = iprot.readI32();
            {
              org.apache.thrift.protocol.TSet _set253 = iprot.readSetBegin(org.apache.thrift.protocol.TType.STRING);
              _val251 = new java.util.HashSet<java.lang.String>(2*_set253.size);
              @org.apache.thrift.annotation.Nullable java.lang.String _elem254;
              for (int _i255 = 0; _i255 < _set253.size; ++_i255)
              {
                _elem254 = iprot.readString();
                _val251.add(_elem254);
              }
            }
            struct.map_int_strset.put(_key250, _val251);
          }
        }
        struct.setMap_int_strsetIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list256 = iprot.readListBegin(org.apache.thrift.protocol.TType.MAP);
          struct.map_int_strset_list = new java.util.ArrayList<java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>>>(_list256.size);
          @org.apache.thrift.annotation.Nullable java.util.Map<java.lang.Integer,java.util.Set<java.lang.String>> _elem257;
          for (int _i258 = 0; _i258 < _list256.size; ++_i258)
          {
            {
              org.apache.thrift.protocol.TMap _map259 = iprot.readMapBegin(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.SET); 
              _elem257 = new java.util.HashMap<java.lang.Integer,java.util.Set<java.lang.String>>(2*_map259.size);
              int _key260;
              @org.apache.thrift.annotation.Nullable java.util.Set<java.lang.String> _val261;
              for (int _i262 = 0; _i262 < _map259.size; ++_i262)
              {
                _key260 = iprot.readI32();
                {
                  org.apache.thrift.protocol.TSet _set263 = iprot.readSetBegin(org.apache.thrift.protocol.TType.STRING);
                  _val261 = new java.util.HashSet<java.lang.String>(2*_set263.size);
                  @org.apache.thrift.annotation.Nullable java.lang.String _elem264;
                  for (int _i265 = 0; _i265 < _set263.size; ++_i265)
                  {
                    _elem264 = iprot.readString();
                    _val261.add(_elem264);
                  }
                }
                _elem257.put(_key260, _val261);
              }
            }
            struct.map_int_strset_list.add(_elem257);
          }
        }
        struct.setMap_int_strset_listIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

