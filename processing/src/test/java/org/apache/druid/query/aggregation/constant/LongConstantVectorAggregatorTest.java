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

package org.apache.druid.query.aggregation.constant;

import org.apache.commons.lang.math.RandomUtils;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

public class LongConstantVectorAggregatorTest
{
  private long randomVal;
  private LongConstantVectorAggregator aggregator;
  private ByteBuffer byteBuffer;

  @Before
  public void setup()
  {
    randomVal = RandomUtils.nextLong();
    aggregator = new LongConstantVectorAggregator(randomVal);
    byteBuffer = EasyMock.mock(ByteBuffer.class);
    EasyMock.replay(byteBuffer);
    EasyMock.verifyUnexpectedCalls(byteBuffer);
  }

  @Test
  public void testAggregate()
  {
    aggregator.aggregate(byteBuffer, 0, 1, 10);
    Assert.assertEquals(randomVal, aggregator.get(byteBuffer, 0));
  }

  @Test
  public void testAggregateWithIndirection()
  {
    aggregator.aggregate(byteBuffer, 2, new int[]{2, 3}, null, 0);
    Assert.assertEquals(randomVal, aggregator.get(byteBuffer, 0));
  }

  @Test
  public void testGet()
  {
    Assert.assertEquals(randomVal, aggregator.get(byteBuffer, 0));
  }
}