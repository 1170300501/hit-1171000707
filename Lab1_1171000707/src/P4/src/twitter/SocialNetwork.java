/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.src.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	try {
    		Map<String,Set<String>> follow = new HashMap<>();
    		Set<String> person = new HashSet<>();//������е�����
    		for(int i = 0;i<tweets.size();i++) {
    			person.add(tweets.get(i).getAuthor().toLowerCase());	
    		}
    		for(String name: person) {
    			List<Tweet> works = Filter.writtenBy(tweets,name); //�ҵ�ÿ������д��������
    			Set<String> re = Extract.getMentionedUsers(works);
    			if(re.contains(name)) {
    				re.remove(name);//�����ص�Set�а������߱��ˣ�����ȥ��
    			}
    			follow.put(name,re);//��������б�@����
    		}
    		return follow;
    	}catch(Exception e) {
    		 throw new RuntimeException("not implemented");
    	}
       
    }
    //���ܣ���������Ϊname������д�����Ĵ�����ͬ���������List
    //����MyGuessFollowsGraph�����ҵ���ÿ���û����������
    public static List<Tweet> MyContaining(List<Tweet> tweets, List<String> words,String name) {
    	try {
    		List<Tweet> result = new ArrayList<Tweet>();
    		
    		Pattern pattern =Pattern.compile("#([A-Za-z]+)");//����������ʽ������ɸѡ������name���������������ĵı�ǩ
    		for(int i = 0;i<tweets.size();i++) {
    			ArrayList<String> s = new ArrayList<>();
    			if(!tweets.get(i).getAuthor().equals(name)) {
    				Matcher topic = pattern.matcher(tweets.get(i).getText());
        			while(topic.find()) {
        				String temp = new String(topic.group(0).toString().toLowerCase());
        				s.add(temp); //��������Сд��ʽд�뼯����
        			}
        		end:	for(int k=0;k<s.size();k++){ //������name�����ı�ǩ���������ߵı�ǩ���бȽϣ��ҵ�����ͬ��ǩ������
        				for(int j = 0;j <words.size();j++) {
        					if(s.get(k).equalsIgnoreCase(words.get(j))) {
        						result.add(tweets.get(i));
        						break end;
        					}
        				}
        			}	
        		}
    			}	
       		return result;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	} 
    }
    //���ܣ��ҵ�ÿ���û�����׷����ˣ�����������������@���û��Լ�
    //�����ߵ����Ĵ�����ͬ��ǩ���û��������˵����Ĵ�����ͬ��ǩ���������п��ܻụ��Ӱ�죬���߻����ע��
   public static Map<String,Set<String>> MyGuessFollowsGraph(List<Tweet> tweets){
	   try {
   		Map<String,Set<String>> Myfollow = new HashMap<>();
   		Set<String> person = new HashSet<>();//����������е�����
   		for(int i = 0;i<tweets.size();i++) {
   			person.add(tweets.get(i).getAuthor().toLowerCase());	
   		}
   		for(String name: person) {
   			List<Tweet> works = Filter.writtenBy(tweets,name); //�ҵ�ÿ������д��������
   			Pattern pattern =Pattern.compile("#([A-Za-z]+)"); //����������ʽɸѡ����
    		for(int i = 0; i<works.size();i++) {
    			Matcher topic = pattern.matcher(works.get(i).getText());
    			List<String> words = new ArrayList<>();
    			while(topic.find()) {
    				String temp = new String(topic.group(0).toString().toLowerCase());
    				words.add(temp); //��������Сд��ʽд�뼯����
    			}
    			Set<String> relation = Extract.getMentionedUsers(works);//�Ƚ�����������@���˼���relation
    			if(words.size() != 0) { //�������name�������д��б�ǩ�����ҵ�������name������ͬ��ǩ���˼��뵽relation��
    				List<Tweet> sametopic = MyContaining(tweets,words,name);
        			for(int j = 0;j <sametopic.size();j++) {
        				relation.add(sametopic.get(j).getAuthor());	
        			}
    			}
    			if(relation.contains(name)) {
    				relation.remove(name);//����б��к������߱��ˣ����Ƴ�
    			}
    			Myfollow.put(name,relation);//��������б�@�����Լ����������Ĵ�����ͬ�������
    		}	
   		}
   		return Myfollow;
   	}catch(Exception e) {
   		 throw new RuntimeException("not implemented");
   	}  
   }
	/**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     */
   public static List<String> influencers(Map<String, Set<String>> followsGraph) {
    	try {
    		List<String> influencers = new ArrayList<>();
    		Map<String,Integer> influenceKey = new HashMap<>();//���ÿ���˵�Ӱ����
    		Set<String> names = followsGraph.keySet(); //�õ����е�������
    		for(String author:names) {
    			for(String person:followsGraph.get(author)) {//��ĳ�����ߵ�����@���˼������Ӱ������map��
    				if(!influenceKey.containsKey(person)) {
    					influenceKey.put(person,1);//�����˵�һ�α�@����Ӱ����Ϊ1
    				}
    				else {
    					influenceKey.put(person,influenceKey.get(person)+1);//���ǵ�һ�α�@������ԭ����Ӱ�������ټ�1
    				}
    			}
    		}
    		Set<String> peoples = influenceKey.keySet();//�õ����е��û�
    		ArrayList<String> key = new ArrayList<String>();//�õ����е��û�
    		for(String people: peoples) {
				key.add(people);
			}
    		String temp;
    		for(int i = 0;i<key.size()-1;i++) { //���õ���ÿ���û���Ӱ��������������
    			int k = i;
    			for(int j =i;j<key.size();j++) {
    				if(influenceKey.get(key.get(j)) > influenceKey.get(key.get(k)))
    					k = j;
    			}
    			if(k != i) {
    				temp = key.get(k);
    				key.set(k,key.get(i));
    				key.set(i,temp);	
    			}
    		}
    		for(int i =0;i<key.size();i++) {
    			influencers.add(key.get(i));
    		}
    		return influencers;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	}
        
    }
}
