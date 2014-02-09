package com.juicer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;

public class NLPTools {
	
	private static final String modelFilename = "/cmu/arktweetnlp/model.20120919";
	private String tweets[];

	public NLPTools() {
		//Sample tweets
		tweets = new String[5];
		tweets[0] = "#teamnycgirls @MTV #teamnycgirls @MTV #teamnycgirls @MTV #teamnycgirls @MTV #teamnycgirls @MTV #teamnycgirls @MTV #teamnycgirls @MTV &lt;3";
		tweets[1] =	"RT @MileyOfficial: Miley Cyrus's @MTV Unplugged is the Highest Rated Unplugged Special in a Decade w/ 1.7 million streams in 24 hours!!! ??";
		tweets[2] = "RT @xchristinnaaa: #TeamKam #dancingwithmiley @MTV";
		tweets[3] = "#teamnycgirls @MTV #teamnycgirls @MTV :D";
		tweets[4] = "#TeamAlyCat #49mins #dancingwithmiley @mtv";
	}
	
	public void runSample() {
		try {
			
			Tagger tagger = new Tagger();
			tagger.loadModel(modelFilename);
			for(String t : tweets) {
				List<TaggedToken> list = tagger.tokenizeAndTag(t);
				for(TaggedToken tt : list) {
					System.out.println("[" + tt.token + "_" + tt.tag + "]");
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NLPTools nlp = new NLPTools();
		nlp.runSample();
	}
	
	
}
