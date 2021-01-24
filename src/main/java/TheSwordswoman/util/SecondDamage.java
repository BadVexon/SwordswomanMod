package TheSwordswoman.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import TheSwordswoman.cards.AbstractBlazCard;

public class SecondDamage extends DynamicVariable {

    @Override
    public String key() {
        return "eded";
    } //TODO: change to something else!

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractBlazCard) card).isSecondDamageModified;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractBlazCard) {
            ((AbstractBlazCard) card).isSecondDamageModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractBlazCard) card).secondDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractBlazCard) card).baseSecondDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractBlazCard) card).upgradedSecondDamage;
    }
}