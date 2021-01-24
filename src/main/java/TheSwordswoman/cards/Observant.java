package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Observant extends AbstractBlazCard {

    public final static String ID = makeID("Observant");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Observant() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        swapStance();
        applyToSelf(new StrengthPower(p, 1));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
                att(new GainBlockAction(p, x));
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}