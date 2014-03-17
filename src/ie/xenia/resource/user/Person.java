package ie.xenia.resource.user;

public class Person {

	public enum Gender { MALE, FEMALE, UNSPEC };
	

	public static class Name {
		private String _first, _last, _nick;

		public String getFirst() { return _first; }
		public String getLast() { return _last; }
		public String getNick() { return _nick; }

		public void setFirst(String s) { _first = s; }
		public void setLast(String s) { _last = s; }
		public void setNick(String s) { _nick = s; }
	}


	private String _password = null;
	private Gender _gender;
	private Name _name;
	private boolean _isVerified;
//	private byte[] _userImage;
	private String _email = null;
	private String _homepage = null;
	private String _weblog = null;
	private String _uri = null;
	private String[] _roles = null;

	public String[] getRoles() { return _roles; }
	public String getPassword() { return _password; }
	public Name getName() { return _name; }
	public boolean isVerified() { return _isVerified; }
	public Gender getGender() { return _gender; }
//	public byte[] getUserImage() { return _userImage; }
	public String getEmail() { return _email; }
	public String getHomepage() { return _homepage; }
	public String getWeblog() { return _weblog; }
	public String getUri() { return _uri; }

	public void setRoles(String[] n) { _roles = n; }
	public void setPassword(String n) { _password = n; }
	public void setName(Name n) { _name = n; }
	public void setVerified(boolean b) { _isVerified = b; }
	public void setGender(Gender g) { _gender = g; }
//	public void setUserImage(byte[] b) { _userImage = b; }
	public void setEmail(String b) { _email = b; }
	public void setHomepage(String b) { _homepage = b; }
	public void setWeblog(String b) { _weblog = b; }
	public void setUri(String b) { _uri = b; }



	public Person(){
		new Person(null, null, null, null, null, null, null);
	}
	public Person(String firstname, String surname, String nickname, String email,
			String homepage, String weblog, String uri){
		Name name = new Name();
		name.setFirst(firstname);
		name.setLast(surname);
		name.setNick(nickname);
		setName(name);
		setEmail(email);
		setHomepage(homepage);
		setWeblog(weblog);
		setUri(uri);
	}

	

}
