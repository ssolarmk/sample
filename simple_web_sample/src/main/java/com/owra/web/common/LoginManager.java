package com.owra.web.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * <pre>
 * Subject : 공통 로그인 처리
 * Comment : 세션권한체크 및 로그인 유저 관리.
 * </pre>
 * @author min
 * @since 2011-02-22
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class LoginManager implements HttpSessionBindingListener, Serializable {

	private static final long serialVersionUID = -6922827609832482843L;

	private static LoginManager loginManager = null;

	// 로그인한 접속자를 담기위한 해시테이블
	@SuppressWarnings("rawtypes")
	private static Hashtable log_user_table = new Hashtable();

	/*
	 * 싱글톤 패턴 사용
	 */
	public static synchronized LoginManager getInstance() 
	{
		if (loginManager == null) 
		{
			loginManager = new LoginManager();
		}
		return loginManager;
	}

	/**
	 * 이 메소드는 세션이 연결되을때 호출된다.
	 *  - session.setAttribute("login", this)
	 *  - Hashtable 에 세션과 접속자 아이디를 저장한다.
	 */
	public void valueBound(HttpSessionBindingEvent event) 
	{
		// session값을 put한다.
		log_user_table.put(event.getSession(), event.getName());
		
		System.out.println("CURRENT USERS : (" + getUserCount() + ")" + "[" + event.getName() + "] is session MADED.");
	}

	/**
	 * 이 메소드는 세션이 끊겼을때 호출된다.
	 *  - Hashtable 에 저장된 로그인한 정보를 제거해 준다.
	 */
	public void valueUnbound(HttpSessionBindingEvent event) 
	{
		// session값을 찾아서 없애준다.
		log_user_table.remove(event.getSession());
		
		System.out.println("CURRENT USERS : (" + getUserCount() + ")" + "[" + event.getName() + "] is session EXPIRE.");
	}

	/**
	 * 입력받은 아이디를 해시테이블에서 삭제.
	 * 
	 * @param userID 사용자 아이디
	 * 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	public void removeSession(String userId) 
	{
		Enumeration e = log_user_table.keys();
		HttpSession session = null;
		
		while (e.hasMoreElements()) 
		{
			session = (HttpSession) e.nextElement();
			
			System.out.println("log_user_table.get(session) : "+log_user_table.get(session));
			
			if (log_user_table.get(session).equals(userId)) 
			{
				// 세션이 invalidate될때 HttpSessionBindingListener를 구현하는 클레스의 valueUnbound()함수가 호출된다.
				session.invalidate();
			}
		}
	}

	/**
	 * 해당 아이디의 동시 사용을 막기위해서 이미 사용중인 아이디인지를 확인한다.
	 * 
	 * @param userID 사용자 아이디 
	 * @return boolean 이미 사용 중인 경우 true, 사용중이 아니면 false
	 */
	public boolean isUsing(String userID) 
	{
		return log_user_table.containsValue(userID);
	}

	/**
	 * 로그인을 완료한 사용자의 아이디를 세션에 저장하는 메소드
	 * 
	 * @param session 세션 객체 
	 * @param userID 사용자 아이디
	 */
	public void setSession(HttpSession session, String userId) 
	{
		// 이순간에 Session Binding이벤트가 일어나는 시점
		// name값으로 userId, value값으로 자기자신(HttpSessionBindingListener를 구현하는 Object)
		session.setAttribute(userId, this);// login에 자기자신을 집어넣는다.
	}

	/**
	 * 입력받은 세션Object로 아이디를 리턴한다.
	 * 
	 * @param session : 접속한 사용자의 session Object 
	 * @return String : 접속자 아이디
	 */
	public String getUserID(HttpSession session) 
	{
		return (String) log_user_table.get(session);
	}

	/**
	 * 현재 접속한 총 사용자 수
	 * 
	 * @return int 현재 접속자 수
	 */
	public int getUserCount() 
	{
		return log_user_table.size();
	}

	/**
	 * 현재 접속중인 모든 사용자 아이디를 출력
	 * 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	public void printloginUsers() 
	{
		Enumeration e = log_user_table.keys();
		
		HttpSession session = null;
		System.out.println("===========================================");
		int i = 0;
		
		while (e.hasMoreElements()) 
		{
			session = (HttpSession) e.nextElement();
			System.out.println((++i) + ". 접속자 : " + log_user_table.get(session));
		}
		System.out.println("===========================================");
	}

	/**
	 * 현재 접속중인 모든 사용자리스트를 리턴
	 * 
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public Collection getUsers()
	{
		Collection collection = log_user_table.values();
		return collection;
	}
}
