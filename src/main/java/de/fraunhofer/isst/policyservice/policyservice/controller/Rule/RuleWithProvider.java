package de.fraunhofer.isst.policyservice.policyservice.controller.Rule;

import de.fraunhofer.iese.ids.odrl.policy.library.model.Action;
import de.fraunhofer.iese.ids.odrl.policy.library.model.Condition;
import de.fraunhofer.iese.ids.odrl.policy.library.model.Party;
import de.fraunhofer.iese.ids.odrl.policy.library.model.Rule;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import lombok.Getter;
import lombok.Setter;


import java.net.URI;
import java.util.ArrayList;

public class RuleWithProvider {
    @Getter
    @Setter
    Party provider;
    RuleType type;
    URI target;
    Action action;
    ArrayList<Condition> constraints;
    ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> preduties;
    ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> postduties;

    public RuleWithProvider() {
    }

    public RuleWithProvider(RuleType type, URI target, Action action) {
        this.type = type;
        this.target = target;
        this.action = action;
    }

    public RuleWithProvider(RuleType type, Action action) {
        this.type = type;
        this.action = action;
    }

    public String toString() {
        return "{    \r\n" + this.getIdsType() + this.action.toString() + this.getPreobligationBlock() + this.getConstraintBlock() + this.getPostobligationBlock() + "\r\n  }";
    }

    private String getIdsType() {
        if (!this.type.equals(RuleType.POSTDUTY) && !this.type.equals(RuleType.PREDUTY)) {
            return this.target != null ? "      \"ids:target\":           \"@id\":\"" + this.target.toString() + "\"\n       ,    \r\n   \"ids:provider\":" + this.getProvider().toString() + "\"\n , \r\n      " : "";
        } else {
            return "      \"@type\":\"ids:Duty\",  \n";
        }
    }

    private String getPreobligationBlock() {
        String preobligationBlock = "";
        if (this.preduties != null && this.preduties.size() > 0) {
            String temp = "";
            temp = ((de.fraunhofer.iese.ids.odrl.policy.library.model.Rule)this.preduties.get(0)).toString();
            if (this.preduties.size() > 1) {
                for(int i = 1; i < this.preduties.size(); ++i) {
                    temp = temp.concat(", \n" + ((de.fraunhofer.iese.ids.odrl.policy.library.model.Rule)this.preduties.get(i)).toString());
                }
            }

            preobligationBlock = String.format(", \r\n    \"ids:preDuty\": [%s] \n", temp);
        }

        return preobligationBlock;
    }

    private String getConstraintBlock() {
        String conditionInnerBlock = "";
        if (this.constraints != null) {
            String conditions = "";

            for(int i = 0; i < this.constraints.size(); ++i) {
                String tempString = ((Condition)this.constraints.get(i)).toString();
                if (!tempString.isEmpty()) {
                    if (conditions.isEmpty() && !tempString.isEmpty()) {
                        conditions = conditions.concat(tempString);
                    } else {
                        conditions = conditions.concat("," + tempString);
                    }
                }
            }

            if (!conditions.isEmpty()) {
                conditionInnerBlock = String.format(",     \r\n      \"ids:constraint\": [%s] ", conditions);
            }
        }

        return conditionInnerBlock;
    }

    private String getPostobligationBlock() {
        String postobligationBlock = "";
        if (this.postduties != null && this.postduties.size() > 0) {
            String temp = "";
            temp = ((de.fraunhofer.iese.ids.odrl.policy.library.model.Rule)this.postduties.get(0)).toString();
            if (this.postduties.size() > 1) {
                for(int i = 1; i < this.postduties.size(); ++i) {
                    temp = temp.concat(", \n" + ((de.fraunhofer.iese.ids.odrl.policy.library.model.Rule)this.postduties.get(i)).toString());
                }
            }

            postobligationBlock = postobligationBlock.concat(String.format(", \r\n    \"ids:postDuty\": [%s] \n", temp));
        }

        return postobligationBlock;
    }

    public RuleType getType() {
        return this.type;
    }

    public URI getTarget() {
        return this.target;
    }

    public Action getAction() {
        return this.action;
    }

    public ArrayList<Condition> getConstraints() {
        return this.constraints;
    }

    public ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> getPreduties() {
        return this.preduties;
    }

    public ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> getPostduties() {
        return this.postduties;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public void setTarget(URI target) {
        this.target = target;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setConstraints(ArrayList<Condition> constraints) {
        this.constraints = constraints;
    }

    public void setPreduties(ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> preduties) {
        this.preduties = preduties;
    }

    public void setPostduties(ArrayList<de.fraunhofer.iese.ids.odrl.policy.library.model.Rule> postduties) {
        this.postduties = postduties;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RuleWithProvider)) {
            return false;
        } else {
            RuleWithProvider other = (RuleWithProvider) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$target = this.getTarget();
                Object other$target = other.getTarget();
                if (this$target == null) {
                    if (other$target != null) {
                        return false;
                    }
                } else if (!this$target.equals(other$target)) {
                    return false;
                }

                Object this$action = this.getAction();
                Object other$action = other.getAction();
                if (this$action == null) {
                    if (other$action != null) {
                        return false;
                    }
                } else if (!this$action.equals(other$action)) {
                    return false;
                }

                label62: {
                    Object this$constraints = this.getConstraints();
                    Object other$constraints = other.getConstraints();
                    if (this$constraints == null) {
                        if (other$constraints == null) {
                            break label62;
                        }
                    } else if (this$constraints.equals(other$constraints)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$preduties = this.getPreduties();
                    Object other$preduties = other.getPreduties();
                    if (this$preduties == null) {
                        if (other$preduties == null) {
                            break label55;
                        }
                    } else if (this$preduties.equals(other$preduties)) {
                        break label55;
                    }

                    return false;
                }

                Object this$postduties = this.getPostduties();
                Object other$postduties = other.getPostduties();
                if (this$postduties == null) {
                    if (other$postduties != null) {
                        return false;
                    }
                } else if (!this$postduties.equals(other$postduties)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RuleWithProvider;
    }

}