package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.TravelJournalPower;

public class TravelJournal extends AbstractBlazCard {

    public final static String ID = makeID("TravelJournal");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public TravelJournal() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TravelJournalPower(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}