package kr.co.bithotel.common.util;

import java.util.Scanner;
import org.apache.ibatis.session.SqlSession;
import kr.co.bithotel.common.db.MyAppSqlConfig;

public class Util {
	private static Scanner sc = new Scanner(System.in);
	private static SqlSession session = MyAppSqlConfig.getSqlSession();
	
	public static String input() {
		return sc.nextLine();
	}
	
	public static String input(String str) {
		System.out.print(str);
		return sc.nextLine();
	}
	
	public static SqlSession getSession() {
		return session;
	}
	
	public static void invalidInput() {
		System.out.println("\n올바르지 않은 입력입니다.");
		System.out.println("다시 입력해주세요.\n");
	}
}