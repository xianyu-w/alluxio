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

package alluxio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods for Alluxio processes.
 */
public final class ProcessUtils {
  private static final Logger LOG = LoggerFactory.getLogger(ProcessUtils.class);

  /**
   * Runs the given process. This method should only be called from {@code main()} methods.
   *
   * @param process the process to run
   * @param name the process name
   */
  public static void run(Process process, String name) {
    try {
      LOG.info("Starting {}", process);
      process.start();
      System.exit(0);
    } catch (Exception e) {
      LOG.error("Uncaught exception while running {}, stopping it and exiting.", name, e);
      try {
        LOG.info("Stopping {}", process);
        process.stop();
      } catch (Exception e2) {
        // continue to exit
        LOG.error("Uncaught exception while stopping {}, simply exiting.", name, e2);
      }
      System.exit(-1);
    }
  }

  private ProcessUtils() {} // prevent instantiation
}
