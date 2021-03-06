package eu.trentorise.game.challenges.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.trentorise.game.challenges.api.Constants;

/**
 * Load challenges rule from and csv file
 *
 * {@link IOUtils}
 */
public final class ChallengeRulesLoader {

	private static final Logger logger = LogManager
			.getLogger(ChallengeRulesLoader.class);

	private static final String[] COLUMNS = { "NAME", "TYPE", "GOAL_TYPE",
			"TARGET", "BONUS", "POINT_TYPE", "DIFFICULTY", "BASELINE_VARIABLE",
			"SELECTION_CRITERIA_POINTS", "SELECTION_CRITERIA_BADGES" };

	private ChallengeRulesLoader() {
	}

	public static ChallengeRules load(String ref) throws IOException,
			NullPointerException, IllegalArgumentException {
		if (ref == null) {
			logger.error("Input file must be not null");
			throw new NullPointerException("Input file must be not null");
		}
		if (!ref.endsWith(".csv")) {
			logger.error("challenges rules file must be a csv file");
			throw new IllegalArgumentException(
					"challenges rules file must be a csv file");
		}
		BufferedReader rdr = null;
		try {

			try {
				// open csv file
				rdr = new BufferedReader(new StringReader(
						IOUtils.toString(Thread.currentThread()
								.getContextClassLoader()
								.getResourceAsStream(ref))));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			} catch (NumberFormatException e) {
				logger.error(e.getMessage(), e);
				return null;
			} catch (NullPointerException npe) {
				rdr = new BufferedReader(new FileReader(ref));
			}
			ChallengeRules response = new ChallengeRules();
			boolean first = true;
			for (String line = rdr.readLine(); line != null; line = rdr
					.readLine()) {
				if (first) {
					first = false;
					continue;
				}
				String[] elements = line.split(";");
				ChallengeRuleRow crr = new ChallengeRuleRow();
				crr.setName(elements[0]);
				crr.setModelName(elements[1]);
				crr.setGoalType(elements[2]);
				if (elements[3] != null && !elements[3].isEmpty()) {
					try {
						crr.setTarget(Double.valueOf(elements[3]));
					} catch (NumberFormatException nfe) {
						logger.debug("Target value is not a number, current challenge is a LeaderboardPosition?");
						crr.setTarget(elements[3]);
					}
				}
				crr.setBonus(Double.valueOf(elements[4]));
				crr.setPointType(elements[5]);
				crr.setBaselineVar(elements[7]);
				if (elements.length > 8) {
					crr.setSelectionCriteriaPoints(elements[8]);
				}
				if (elements.length > 9) {
					crr.setSelectionCriteriaBadges(elements[9]);
				}
				response.getChallenges().add(crr);
			}
			logger.debug("Rows in file " + response.getChallenges().size());
			return response;
		} finally {
			if (rdr != null) {
				rdr.close();
			}
		}
	}

	public static void write(File f, ChallengeRules rules) throws IOException,
			IllegalArgumentException {
		if (f == null) {
			logger.error("Target file must be not null");
			throw new IllegalArgumentException("Target file must be not null");
		}
		if (rules == null) {
			logger.error("Rules must be not null");
			throw new IllegalArgumentException("Rules must be not null");
		}
		FileOutputStream fos = null;
		try {
			StringBuffer toWrite = new StringBuffer();
			toWrite.append(StringUtils.join(COLUMNS, ";")
					+ Constants.LINE_SEPARATOR);
			for (ChallengeRuleRow row : rules.getChallenges()) {
				toWrite.append(row.getName() + ";");
				toWrite.append(row.getModelName() + ";");
				toWrite.append(row.getGoalType() + ";");
				toWrite.append(row.getTarget() + ";");
				toWrite.append(row.getBonus() + ";");
				toWrite.append(row.getPointType() + ";");
				toWrite.append(";");
				toWrite.append(row.getBaselineVar() + ";");
				toWrite.append(row.getSelectionCriteriaPoints() + ";");
				toWrite.append(row.getSelectionCriteriaBadges() + ";");
				toWrite.append(Constants.LINE_SEPARATOR);
			}
			fos = new FileOutputStream(f);
			IOUtils.write(toWrite.toString(), fos);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (fos != null) {
				fos.flush();
				fos.close();
			}
		}
	}
}
