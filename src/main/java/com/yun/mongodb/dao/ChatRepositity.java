package com.yun.mongodb.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.yun.chat.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class ChatRepositity {
    @Autowired
    private MongoOperations mongo;

    public void insert(Message message){
//        mongo.insert(message);
        mongo.save(message,"message");//第二个参数是要保存的文档存储的名称
    }

    public AggregationResults<Message> queryHistoryMessage(String from, String to){
        DBObject and1 = new BasicDBObject();
        and1.put("sender", from);
        and1.put("sendTo", to);

        DBObject and2 = new BasicDBObject();
        and1.put("sender", to);
        and1.put("sendTo", from);

        DBObject queryCondition = new BasicDBObject();
        BasicDBList values = new BasicDBList();
        values.add(and1);
        values.add(and2);
        queryCondition.put("$or", values);
        mongo.find(Query.query(
                Criteria.where("sender").is(from)
                        .and("sendTo").is(to)),Message.class);
        mongo.getCollection("chatMessage");
        TypedAggregation<Message> agg = Aggregation.newAggregation(
                Message.class,
//                unwind("classKey"),
//                project("evaluate", "classKey"),
                  match(Criteria.where("sender").is(from).and("sendTo").is(to)),
                match(Criteria.where("sender").is(to).and("sendTo").is(from)),
//                group("evaluate", "classKey").count().as("totalNum"),
                sort(Sort.Direction.DESC, "sendTime")
        );
        AggregationResults<Message> result = mongo.aggregate(agg, Message.class);
        return result;
    }
}
