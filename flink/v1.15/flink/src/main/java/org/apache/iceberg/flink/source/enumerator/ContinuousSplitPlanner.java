/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iceberg.flink.source.enumerator;

import java.io.Closeable;
import org.apache.flink.annotation.Internal;

/**
 * This interface is introduced so that we can plug in different split planner for unit test
 */
@Internal
public interface ContinuousSplitPlanner extends Closeable {

  /**
   * Discover the files appended between {@code lastPosition} and current table snapshot
   */
  ContinuousEnumerationResult planSplits(IcebergEnumeratorPosition lastPosition);
}