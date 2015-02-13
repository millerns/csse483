/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-01-14 17:53:03 UTC)
 * on 2015-02-13 at 16:46:18 UTC 
 * Modify at your own risk.
 */

package com.appspot.millerns_grade_recorder.graderecorder.model;

/**
 * Model definition for GradeEntry.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the graderecorder. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class GradeEntry extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("assignment_key")
  private java.lang.String assignmentKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String entityKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long score;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_key")
  private java.lang.String studentKey;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAssignmentKey() {
    return assignmentKey;
  }

  /**
   * @param assignmentKey assignmentKey or {@code null} for none
   */
  public GradeEntry setAssignmentKey(java.lang.String assignmentKey) {
    this.assignmentKey = assignmentKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEntityKey() {
    return entityKey;
  }

  /**
   * @param entityKey entityKey or {@code null} for none
   */
  public GradeEntry setEntityKey(java.lang.String entityKey) {
    this.entityKey = entityKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getScore() {
    return score;
  }

  /**
   * @param score score or {@code null} for none
   */
  public GradeEntry setScore(java.lang.Long score) {
    this.score = score;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStudentKey() {
    return studentKey;
  }

  /**
   * @param studentKey studentKey or {@code null} for none
   */
  public GradeEntry setStudentKey(java.lang.String studentKey) {
    this.studentKey = studentKey;
    return this;
  }

  @Override
  public GradeEntry set(String fieldName, Object value) {
    return (GradeEntry) super.set(fieldName, value);
  }

  @Override
  public GradeEntry clone() {
    return (GradeEntry) super.clone();
  }

}
