package TheSwordswoman.cards;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.BlazMod;

public class Valiance extends AbstractBlazCard {

    public final static String ID = makeID("Valiance");

    //stupid intellij stuff SKILL, SELF, COMMON

    public Valiance() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 2;
        tags.add(BlazMod.TRANCE);
        cardsToPreview = new Dazed();
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void trance(AbstractMonster m) {
        shuffleIn(new Dazed(), magicNumber);
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(-1);
    }
}