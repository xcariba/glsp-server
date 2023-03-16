/********************************************************************************
 * Copyright (c) 2020-2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.eclipse.glsp.server.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;

import org.apache.log4j.Logger;
import org.eclipse.glsp.server.launch.DefaultCLIParser;

@SuppressWarnings("rawtypes")
public final class LaunchUtil {
   private static Logger LOGGER = Logger.getLogger(LaunchUtil.class);

   private LaunchUtil() {}

   public static final class DefaultOptions {
      public static final int SERVER_PORT = 5007;
      public static final Level LOG_LEVEL = Level.INFO;
      public static final String LOG_DIR = new File("./logs/").getAbsolutePath();
      public static final boolean CONSOLE_LOG_ENABLED = true;
      public static final boolean FILE_LOG_ENABLED = false;
   }

   public static boolean isValidPort(final Integer port) {
      return port >= 0 && port <= 65535;
   }

   public static void configure(final DefaultCLIParser cli) throws ParseException {
      if (cli.isHelp()) {
         cli.printHelp();
         System.exit(0);
      }
      if (cli.hasOption(DefaultCLIParser.OPTION_LOG_DIR) && !cli.isFileLog()) {
         LOGGER.warn(String.format("File logging is disabled. The option '--%s' will be ignored.",
            DefaultCLIParser.OPTION_LOG_DIR));
      }
   }

   public static void printHelp(final String processName, final Options options) {
      HelpFormatter formatter = new HelpFormatter();
      String cmdLineSyntax = "java -jar " + processName;
      formatter.printHelp(90, cmdLineSyntax, "\noptions:", options, "", true);
   }
}
