 /*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensoc.enrichment.adapters.geo;

import java.util.Properties;

import org.json.simple.JSONObject;

import com.opensoc.test.AbstractTestContext;

 /**
 * <ul>
 * <li>Title: </li>
 * <li>Description: </li>
 * <li>Created: Aug 25, 2014 by: spiddapa</li>
 * </ul>
 * @author $Author: spiddapa $
 * @version $Revision: 1.1 $
 */
public class GeoMysqlAdapterTest extends AbstractTestContext  {

    private static GeoMysqlAdapter geoMySqlAdapter=null;
    private static boolean connected=false;

    /**
     * Constructs a new <code>GeoMysqlAdapterTest</code> instance.
     * @param name
     */

    public GeoMysqlAdapterTest(String name) {
        super(name);
        super.setMode(System.getProperty("mode"));
    }

    /**
     
     * @throws java.lang.Exception
     */
    protected static void setUpBeforeClass() throws Exception {
    }

    /**
     
     * @throws java.lang.Exception
     */
    protected static void tearDownAfterClass() throws Exception {
    }

    /* 
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */

    protected void setUp() throws Exception {
        super.setUp();
        Properties prop = super.getTestProperties();
        assertNotNull(prop);   
        System.out.println("username ="+(String)prop.get("mysql.username"));
        if(skipTests(this.getMode())){
            System.out.println(getClass().getName()+" Skipping Tests !!Local Mode");
            return;//skip tests
       }else{
        geoMySqlAdapter=new GeoMysqlAdapter((String)prop.get("mysql.ip"), (new Integer((String)prop.get("mysql.port"))).intValue(),(String)prop.get("mysql.username"),(String)prop.get("mysql.password"), (String)prop.get("bolt.enrichment.geo.adapter.table"));
        connected =geoMySqlAdapter.initializeAdapter();
        assertTrue(connected);
       }
    }

    /* 
     * (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */

    protected void tearDown() throws Exception {
        super.tearDown();
        geoMySqlAdapter=null;
    }

    /**
     * Test method for {@link com.opensoc.enrichment.adapters.geo.GeoMysqlAdapter#enrich(java.lang.String)}.
     */
    public void testEnrich() {
        if(skipTests(this.getMode())){
            return;//skip tests
       }else{
        JSONObject json = geoMySqlAdapter.enrich("72.163.4.161");
        
        //assert Geo Response is not null
        assertNotNull(json);
        
        //assert LocId is not null
        assertNotNull(json.get("locID"));
        
        //assert right LocId is being returned
        assertEquals("4522",json.get("locID"));
       }
    }

    /**
     * Test method for {@link com.opensoc.enrichment.adapters.geo.GeoMysqlAdapter#initializeAdapter()}.
     */
    public void testInitializeAdapter() {
        if(skipTests(this.getMode())){
            return;//skip tests
       }else{        
        boolean connected =geoMySqlAdapter.initializeAdapter();
        assertTrue(connected);
       }
    }
 
    /**
     * Test method for {@link com.opensoc.enrichment.adapters.geo.GeoMysqlAdapter#GeoMysqlAdapter(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String)}.
     */
    public void testGeoMysqlAdapter() {
        if(skipTests(this.getMode())){
            return;//skip tests
       }else{       
           assertTrue(connected);
       }
    }

    /**
     * Returns the geoMySqlAdapter.
     * @return the geoMySqlAdapter.
     */
    
    public static GeoMysqlAdapter getGeoMySqlAdapter() {
        return geoMySqlAdapter;
    }

    /**
     * Sets the geoMySqlAdapter.
     * @param geoMySqlAdapter the geoMySqlAdapter.
     */
    
    public static void setGeoMySqlAdapter(GeoMysqlAdapter geoMySqlAdapter) {
    
        GeoMysqlAdapterTest.geoMySqlAdapter = geoMySqlAdapter;
    }
}

