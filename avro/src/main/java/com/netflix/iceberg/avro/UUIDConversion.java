/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.iceberg.avro;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericFixed;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

public class UUIDConversion extends Conversion<UUID> {
  @Override
  public Class<UUID> getConvertedType() {
    return UUID.class;
  }

  @Override
  public String getLogicalTypeName() {
    return LogicalTypes.uuid().getName();
  }

  @Override
  public UUID fromFixed(GenericFixed value, Schema schema, LogicalType type) {
    ByteBuffer buffer = ByteBuffer.wrap(value.bytes());
    buffer.order(ByteOrder.BIG_ENDIAN);
    long mostSigBits = buffer.getLong();
    long leastSigBits = buffer.getLong();
    return new UUID(mostSigBits, leastSigBits);
  }

  @Override
  public GenericFixed toFixed(UUID value, Schema schema, LogicalType type) {
    ByteBuffer buffer = ByteBuffer.allocate(16);
    buffer.order(ByteOrder.BIG_ENDIAN);
    buffer.putLong(value.getMostSignificantBits());
    buffer.putLong(value.getLeastSignificantBits());
    return new GenericData.Fixed(schema, buffer.array());
  }
}
