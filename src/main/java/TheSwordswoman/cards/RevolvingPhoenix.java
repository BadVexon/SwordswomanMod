package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import TheSwordswoman.powers.RevolvingPhoenixPower;

public class RevolvingPhoenix extends AbstractBlazCard {

    public final static String ID = makeID("RevolvingPhoenix");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public RevolvingPhoenix() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) {
            applyToSelf(new FrailPower(p, 1, false));
        }
        applyToSelf(new RevolvingPhoenixPower(1));
    }

    public void upp() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}