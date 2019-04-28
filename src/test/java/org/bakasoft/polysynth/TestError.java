package org.bakasoft.polysynth;

public class TestError extends AssertionError {

  private final TestCase testCase;

  public TestError(TestCase testCase, String message) {
    this(testCase, message, null);
  }

  public TestError(TestCase testCase, String message, Throwable cause) {
    super(message, cause);
    this.testCase = testCase;
  }

  public TestCase getTestCase() {
    return testCase;
  }

}
