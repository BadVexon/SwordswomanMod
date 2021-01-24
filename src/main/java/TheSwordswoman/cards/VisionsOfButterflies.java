package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class VisionsOfButterflies extends AbstractBlazCard {

    public final static String ID = makeID("VisionsOfButterflies");

    //stupid intellij stuff SKILL, SELF, RARE

    public VisionsOfButterflies() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new CalculatedGambleAction(false));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = AbstractDungeon.player.hand.size();
                att(new ApplyPowerAction(p, p, new LoseStrengthPower(p, x), x));
                att(new ApplyPowerAction(p, p, new StrengthPower(p, x), x));
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}