package TheSwordswoman.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import TheSwordswoman.cards.AbstractBlazCard;

public class SillyVariable extends DynamicVariable {

    @Override
    public String key() {
        return "sillyblue";
    } //TODO: Change this so your mod doesn't conflict!

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractBlazCard) card).isSillyModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractBlazCard) card).silly;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractBlazCard) {
            ((AbstractBlazCard) card).isSillyModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractBlazCard) card).baseSilly;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractBlazCard) card).upgradedSilly;
    }
}