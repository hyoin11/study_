package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class SKP_Friend {
	private Collection<SKP_Friend> friends;
	private String email;
	
	public SKP_Friend(String email) {
		this.email = email;
		this.friends = new ArrayList<SKP_Friend>();
	}
	
	public String getEmail() {
		return email;
	}
	
	public Collection<SKP_Friend> getFriends(){
		return friends;
	}
	
	public void addFriendship(SKP_Friend friend) {
		friends.add(friend);
		friend.getFriends().add(this);
	}
	
	public boolean canBeConnected(SKP_Friend friend) {
		String myEmail = this.getEmail();
		
		String friendEmail = friend.email;
		
		boolean[] visited = new boolean['Z'+1-65];
		visited[friendEmail.charAt(0)-65] = true;
		
		Queue<SKP_Friend> que = new LinkedList<>();
		que.add(friend);
		
//		Collection<Friend> test = friend.getFriends();
//		for(Friend test2 : test) {
//			System.out.println("!" + test2.email);
//		}
		
		while(!que.isEmpty()) {
			SKP_Friend nextFriend = que.poll();
			
			Collection<SKP_Friend> friendList = nextFriend.getFriends();
			for(SKP_Friend friend2 : friendList) {
				String nextFriendEmail = friend2.getEmail();
				
				if(!visited[nextFriendEmail.charAt(0)-65]) {
					if(nextFriendEmail.equals(myEmail)) return true;
					
					que.add(friend2);
					visited[nextFriendEmail.charAt(0)-65] = true;
				}
			}
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		SKP_Friend a = new SKP_Friend("A");
		SKP_Friend b = new SKP_Friend("B");
		SKP_Friend c = new SKP_Friend("C");
//		Friend d = new Friend("D");
		
		a.addFriendship(b);
		b.addFriendship(c);
		
		System.out.println(a.canBeConnected(c));
		
		
	}
}
