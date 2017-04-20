/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.worker.keyvalue;

import alluxio.Configuration;
import alluxio.PropertyKey;
import alluxio.Registry;
import alluxio.worker.Worker;
import alluxio.worker.WorkerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Factory to create a {@link KeyValueWorker} instance.
 */
@ThreadSafe
public final class KeyValueWorkerFactory implements WorkerFactory {
  private static final Logger LOG = LoggerFactory.getLogger(KeyValueWorkerFactory.class);

  /**
   * Constructs a new {@link KeyValueWorkerFactory}.
   */
  public KeyValueWorkerFactory() {}

  @Override
  public boolean isEnabled() {
    return Configuration.getBoolean(PropertyKey.KEY_VALUE_ENABLED);
  }

  @Override
  public KeyValueWorker create(Registry<Worker> registry) {
    if (!isEnabled()) {
      return null;
    }
    LOG.info("Creating {} ", KeyValueWorker.class.getName());
    return new KeyValueWorker(registry);
  }
}
