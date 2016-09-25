package com.training.log.processor.job;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.log.processor.commons.Constants;
import com.training.log.processor.dto.SessionLogDTO;
import com.training.log.processor.utils.BundelUtils;
import com.training.log.processor.utils.DatesUtils;
import com.training.log.processor.utils.LogParser;

import scala.Tuple2;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class Module1Job {

	private static Logger sLogger = LoggerFactory.getLogger(Module1Job.class);

	public static void logToIPSessions(String filename) {
		sLogger.debug(BundelUtils.get("debug.start.job"));
		System.setProperty("hadoop.home.dir", "c:/Hadoop");

		// Define a configuration to use to interact with Spark
		SparkConf lConf = new SparkConf().setMaster(BundelUtils.get("spark.master"))
				.setAppName(BundelUtils.get("spark.app.name"));

		// Create a Java version of the Spark Context from the configuration
		JavaSparkContext lSparkctx = new JavaSparkContext(lConf);

		// Load the input data, which is a text file read from the command line
		JavaRDD<String> lInput = lSparkctx.textFile(filename);

		// Each line will be translate to a session defined by the IP adress
		JavaPairRDD<String, SessionLogDTO> lsession = lInput
				.mapToPair(w -> new Tuple2<String, SessionLogDTO>(LogParser.getFirstToken(w), LogParser.parseTokenz(w)))
				.reduceByKey((a, b) -> reduceByIP(a, b));

		// Save the word count back out to a text file, causing evaluation.
		FileUtils.deleteQuietly(new File(BundelUtils.get("suffix.for.result.file")));
		lsession.saveAsTextFile(BundelUtils.get("suffix.for.result.file"));
	}

	/**
	 * 
	 * @param iSession_1
	 * @param iSession_2
	 * @return null si les sessions sont indépendante sinon une session qui les
	 *         combines
	 */
	public static SessionLogDTO reduceByIP(SessionLogDTO iSession_1, SessionLogDTO iSession_2) {

		long lDuree = DatesUtils.getDateDiff(iSession_1.getmD_Connection(), iSession_2.getmD_Connection(),
				TimeUnit.MILLISECONDS);

		if (lDuree > Constants.sZero) {
			// set date deconnection
			iSession_1.setmD_Deconnection(iSession_2.getmD_Connection());
			// sort list request
			iSession_1.getmListRequetes().addAll(iSession_2.getmListRequetes());
			iSession_1.getmListUrl().addAll(iSession_2.getmListUrl());

		} else {
			// set date deconnection
			iSession_1.setmD_Deconnection(iSession_1.getmD_Connection());
			iSession_1.setmD_Connection(iSession_2.getmD_Connection());

			iSession_2.getmListRequetes().addAll(iSession_1.getmListRequetes());
			iSession_2.getmListUrl().addAll(iSession_1.getmListUrl());
			iSession_1.setmListRequetes(iSession_2.getmListRequetes());
			iSession_1.setmListUrl(iSession_2.getmListUrl());

		}
		iSession_1.setmDuree(Math.abs(lDuree));
		iSession_1.setmNbrPagesVisitees(iSession_1.getmNbrPagesVisitees() + iSession_2.getmNbrPagesVisitees());
		return iSession_1;
	}

	// Test
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println(BundelUtils.get("msg.error.argument.run.jar"));
			System.exit(0);
		}

		logToIPSessions(args[0]);
	}
}
