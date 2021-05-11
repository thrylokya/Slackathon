package com.slackathon.whatifollow1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.slack.api.methods.response.views.ViewsPublishResponse;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;

public class SpringRestClient {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
	/*	List<Blocks> blocks = new ArrayList<>();
		
		Blocks sectionBlock = new Blocks();
		sectionBlock.setType("section");
		Text sectionText = new Text();
		sectionText.setType("mrkdwn");
		sectionText.setText("<http://helloworld-7vs1668.slack.com/archives/C01R6H88VD1/p1620569874000700|@Thrylokya Geereddy has posted the message on #General>");
		sectionBlock.setText(sectionText);
		blocks.add(sectionBlock);

		Blocks dividerBlock = new Blocks();
		dividerBlock.setType("divider");
		blocks.add(dividerBlock);
		
		Blocks richTextBlock = new Blocks();
		richTextBlock.setType("rich_text");
		richTextBlock.setBlock_id("Ngl");
		
		List<Elements> elements = new ArrayList<>();
		Elements rtsEle = new Elements();
		rtsEle.setType("rich_text_section");
		
		List<Elements> innerElements = new ArrayList<>();
		Elements textElement = new Elements();
		textElement.setType("text");
		textElement.setText("this is the message to check json ");
		innerElements.add(textElement);
		
		Elements emojiElement = new Elements();
		emojiElement.setType("emoji");
		emojiElement.setText("slightly_smiling_face");
		innerElements.add(emojiElement);
		
		Elements textElement2 = new Elements();
		textElement2.setType("text");
		textElement2.setText(" ");
		innerElements.add(textElement2);
		
		Elements emojiElement2 = new Elements();
		emojiElement2.setType("emoji");
		emojiElement2.setText("smile");
		innerElements.add(emojiElement2);
		rtsEle.setElements(innerElements);
		elements.add(rtsEle);
		richTextBlock.setElements(elements);
		blocks.add(richTextBlock);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(blocks));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		File file = new ClassPathResource("staticModals/viewPublish.json").getFile();
		FileReader fileReader = new FileReader(file);
		JSONParser jsonParser = new JSONParser(fileReader);
		Object obj = jsonParser.parse();
		
        System.out.println(obj.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer xoxb-1856586297971-2057635471889-QmG5qxQTUN9eENk1X9ZHNFL9");
		String url = WhatIFollowContants.followAppHomeView+"?user=U01RCG5NEFN"+"pretty=1";
		HttpEntity request = new HttpEntity(obj.toString(), headers);
		ResponseEntity<ViewsPublishResponse> response = restTemplate.exchange(url,  HttpMethod.POST,
		        request,
		        ViewsPublishResponse.class,
		        1);
		System.out.println(response.toString());
	}
}
