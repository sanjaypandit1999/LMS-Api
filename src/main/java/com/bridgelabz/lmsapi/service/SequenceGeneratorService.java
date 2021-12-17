package com.bridgelabz.lmsapi.service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsapi.model.DatabaseSequence;

/**
 *purpose to generate auto increment id in mongoDb
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Service
public class SequenceGeneratorService {

	/**
	 *Inject object in this class using by  @Autowired
	 */
	@Autowired
    private  MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     *purpose to generate sequence id in mongoDb
     * 
     * @param seqName
     *@return sequence object
     */
    public  long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

}
