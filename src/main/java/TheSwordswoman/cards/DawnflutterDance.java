package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class DawnflutterDance extends AbstractBlazCard {

    public final static String ID = makeID("DawnflutterDance");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 24;

    public DawnflutterDance() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        applyToSelf(new StrengthPower(AbstractDungeon.player, 3));
        if (upgraded) {
            applyToSelf(new VigorPower(AbstractDungeon.player, 5));
        }
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        applyToSelf(new BufferPower(AbstractDungeon.player, 2));
        if (upgraded) {
            addToBot(new RemoveDebuffsAction(AbstractDungeon.player));
        }
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}