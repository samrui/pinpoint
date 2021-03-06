/*
 * Copyright 2018 Naver Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance,the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.plugin.mongodb;

import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.navercorp.pinpoint.plugin.AgentPath;
import com.navercorp.pinpoint.test.plugin.Dependency;
import com.navercorp.pinpoint.test.plugin.PinpointAgent;
import com.navercorp.pinpoint.test.plugin.PinpointPluginTestSuite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author Roy Kim
 */
@RunWith(PinpointPluginTestSuite.class)
@PinpointAgent(AgentPath.PATH)
@Dependency({
        "org.mongodb:mongodb-driver:[3.2.0,3.3.max]",
        "de.flapdoodle.embed:de.flapdoodle.embed.mongo:2.0.0"
})
public class MongoDBIT_3_2_x_IT extends MongoDBITBase {

    private static com.mongodb.MongoClient mongoClient;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        version = 3.2;
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Override
    public void setClient() {
        mongoClient = new com.mongodb.MongoClient("localhost", 27018);
        database = mongoClient.getDatabase("myMongoDbFake").withReadPreference(ReadPreference.secondaryPreferred()).withWriteConcern(WriteConcern.MAJORITY);
    }

    @Override
    public void closeClient() {
        mongoClient.close();
    }
}
