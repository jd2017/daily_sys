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

    public void save(Message message){
//        mongo.insert(message);
        mongo.save(message,"chatmessage");//第二个参数是要保存的文档存储的名称
    }

    public AggregationResults<Message> queryHistoryMessage(String from, String to){
//        $project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。
//        $match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
//        $limit：用来限制MongoDB聚合管道返回的文档数。
//        $skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。
//        $unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
//        $group：将集合中的文档分组，可用于统计结果。
//        $sort：将输入文档排序后输出。
//        $geoNear：输出接近某一地理位置的有序文档。
        Criteria cr1 = Criteria.where("sender").is(from).and("sendTo").is(to);
        Criteria cr2 = Criteria.where("sender").is(to).and("sendTo").is(from);
        Criteria cr = new Criteria();
        TypedAggregation<Message> agg = Aggregation.newAggregation(
                Message.class,
                match(cr.orOperator(cr1,cr2)),
                sort(Sort.Direction.ASC, "sendTime")
        );
        AggregationResults<Message> result = mongo.aggregate(agg, Message.class);
        return result;
    }
}
