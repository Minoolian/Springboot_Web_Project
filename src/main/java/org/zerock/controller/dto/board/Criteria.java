package org.zerock.controller.dto.board;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Data
public class Criteria {
    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public Criteria(){
        this(1,10);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum=pageNum;
        this.amount=amount;
    }

    public Map<String, Object> getTypeArr(){

        String[] strings = type == null ? new String[]{} : type.split("");
        Map<String,Object> spec=new HashMap<>();

        for(String k:strings){
            if(k.equals("T")) {spec.put("title",keyword);}
            else if(k.equals("W")) {spec.put("writer",keyword);}
            else if(k.equals("C")) {spec.put("content",keyword);}
        }

        return spec;
    }

    public String getListLink(){
        UriComponentsBuilder builder=UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.getPageNum())
                .queryParam("amount", this.getAmount())
                .queryParam("type", this.getType())
                .queryParam("keyword", this.getKeyword());

        return builder.toUriString();
    }

}
