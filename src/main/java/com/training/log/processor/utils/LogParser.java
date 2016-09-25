package com.training.log.processor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.log.processor.commons.Constants;
import com.training.log.processor.dto.SessionLogDTO;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class LogParser {

	private static final Pattern PATTERN = Pattern.compile(Constants.slog_entry_pattern);

	/**
	 * 
	 * @param ilogline
	 * @return
	 * @throws ParseException
	 */
	public static SessionLogDTO parseFromLogLine(String ilogline) {
		SimpleDateFormat lFormat = new SimpleDateFormat(BundelUtils.get("date.timezone.zone.patterne"),
				new Locale(BundelUtils.get("date.locale.en")));
		try {
			Matcher lMatcher = PATTERN.matcher(ilogline);
			if (!lMatcher.find()) {
				System.out.println(BundelUtils.get("debug.error.parse"));
			}

			// La Ligne est toujours composÃ©s de:
			// 1:IP 2:client 3:user 4:date time 5:method 6:req 7:proto
			// 8:respcode
			// 9:size

			return new SessionLogDTO(lMatcher.group(Constants.sUn),
					lFormat.parse(lMatcher.group(Constants.sQuatr) + " " + lMatcher.group(Constants.sCinq)),
					lMatcher.group(Constants.sSis), lMatcher.group(Constants.sSept));
		} catch (ParseException e) {
			System.out.println("ip non trouvée");
			return null;
		}
	}

	/**
	 * 
	 * @param iline
	 * @return le premier token qui apparait avant un espace
	 */
	public static String getFirstToken(String iline) {
		return iline.split(" ")[0];
	}

	public static String getFirstTokenfromString(String iLine) {
		StringTokenizer lMultiTokenizer = new StringTokenizer(iLine, BundelUtils.get("token.spliter"));
		return lMultiTokenizer.nextToken();
	}

	public static SessionLogDTO parseTokenz(String iline) {
		StringTokenizer lMultiTokenizer = new StringTokenizer(iline, BundelUtils.get("token.spliter"));
		SimpleDateFormat format = new SimpleDateFormat(BundelUtils.get("date.timezone.zone.patterne"),
				new Locale(BundelUtils.get("date.locale.en")));
		SessionLogDTO lResult = new SessionLogDTO();
		lResult.setmIp(lMultiTokenizer.nextToken());
		lMultiTokenizer.nextToken();
		lMultiTokenizer.nextToken();
		try {
			lResult.setmD_Connection(format.parse(lMultiTokenizer.nextToken() + " " + lMultiTokenizer.nextToken()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> listRequet = new ArrayList<>();
		listRequet.add(lMultiTokenizer.nextToken());
		lResult.setmListRequetes(listRequet);

		List<String> listUrl = new ArrayList<>();
		listUrl.add(lMultiTokenizer.nextToken());
		lResult.setmListUrl(listUrl);

		lResult.setmNbrPagesVisitees(1);
		return lResult;
	}

	public static Date getTimeStamp(String iLine) throws java.text.ParseException {
		StringTokenizer lMultiTokenizer = new StringTokenizer(iLine, BundelUtils.get("token.spliter"));
		SimpleDateFormat format = new SimpleDateFormat(BundelUtils.get("date.timezone.zone.patterne"),
				new Locale(BundelUtils.get("date.locale.en")));
		return format.parse(lMultiTokenizer.nextToken());
	}

	//Test
	public static void main(String[] args) {
		String s = "64.242.88.10 - - [07/Mar/2004:16:58:54 -0800] \"GET /mailman/listinfo/administration HTTP/1.1\" 200 6459";
		parseTokenz(s);
	}

}
