/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Balaji
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({charlie.bs.section1.Test00_12_3.class, charlie.bs.section1.Test00_12_4.class, charlie.bs.section1.Test00_15_7.class, charlie.bs.section1.Test00_16_6.class 
    ,charlie.bs.section1.Test00_11_1.class,charlie.bs.section1.Test00_10_9.class,charlie.bs.section1.Test00_9_2.class, charlie.bs.section1.Test00_7_7.class })

public class charlie_test_suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
