/*
 * Corona-Warn-App
 *
 * SAP SE and all other contributors /
 * copyright owners license this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package app.coronawarn.server.services.distribution.assembly.exposureconfig.validation;

import app.coronawarn.server.common.protocols.internal.RiskScoreClass;
import app.coronawarn.server.common.protocols.internal.RiskScoreClassification;
import java.util.List;

/**
 * The RiskScoreClassificationValidator checks the values of a given {@link RiskScoreClassification}. instance.
 */
public class RiskScoreClassificationValidator {

  static final int RISK_SCORE_VALUE_RANGE = 256;

  private final RiskScoreClassification riskScoreClassification;

  private ValidationResult errors;

  public RiskScoreClassificationValidator(RiskScoreClassification riskScoreClassification) {
    this.riskScoreClassification = riskScoreClassification;
  }

  /**
   * Performs a validation of the associated {@link RiskScoreClassification} instance and returns information about
   * validation failures.
   *
   * @return The ValidationResult instance, containing information about possible errors.
   */
  public ValidationResult validate() {
    errors = new ValidationResult();

    List<RiskScoreClass> riskScoreClasses = riskScoreClassification.getRiskScoreClassesList();

    validateMinMaxValue();
    validateValueRangeCoverage();

    return errors;
  }

  private void validateMinMaxValue() {
    for (RiskScoreClass riskScoreClass : riskScoreClassification.getRiskScoreClassesList()) {
      int minRiskLevel = riskScoreClass.getMinRiskLevel();
      int maxRiskLevel = riskScoreClass.getMaxRiskLevel();

      if (minRiskLevel > maxRiskLevel) {
        // TODO add error
      }
    }
  }

  private void validateRiskScoreValueBounds(int value) {
    if (value < 0) {
      // TODO error
    } else if (value > RISK_SCORE_VALUE_RANGE - 1) {
      // TODO error
    }
  }

  private void validateValueRangeCoverage() {
    int partitionSum = riskScoreClassification.getRiskScoreClassesList().stream()
        .mapToInt(riskScoreClass -> (riskScoreClass.getMaxRiskLevel() - riskScoreClass.getMinRiskLevel() + 1))
        .sum();

    if (partitionSum != RISK_SCORE_VALUE_RANGE) {
      // TODO add error
    }
  }

  private void validateUrl(String url) {
    if (!url.isBlank()) {
      // TODO check if valid URL
    }
  }
}
