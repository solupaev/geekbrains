package com.geekbrains.gwt;

import com.geekbrains.gwt.client.GwtAppTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GwtAppSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for GwtApp");
    suite.addTestSuite(GwtAppTest.class);
    return suite;
  }
}
