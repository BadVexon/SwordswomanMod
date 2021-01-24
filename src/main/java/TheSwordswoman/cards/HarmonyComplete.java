package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HarmonyComplete extends AbstractBlazCard {

    public final static String ID = makeID("HarmonyComplete");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = -1;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public HarmonyComplete() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 4;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
 stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = AbstractDungeon.player.hand.size();
                for (int i = 0; i < magicNumber; i++) {
                    atb(new GainBlockAction(AbstractDungeon.player, x));
                }
            }
        });
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        atb(new DrawCardAction(silly));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                baseDamage = AbstractDungeon.player.hand.size();
                applyPowers();
                calculateCardDamage(m);
                att(new DamageAction(m, makeInfo(), AttackEffect.FIRE));
            }
        });
        atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 3, false));
    }

    @Override
    public void abc(AbstractMonster m) {
        atb(new DrawCardAction(silly));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                baseDamage = AbstractDungeon.player.hand.size();
                applyPowers();
                calculateCardDamage(m);
                att(new DamageAction(m, makeInfo(), AttackEffect.FIRE));
            }
        });
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = AbstractDungeon.player.hand.size();
                for (int i = 0; i < magicNumber; i++) {
                    atb(new GainBlockAction(AbstractDungeon.player, x));
                }
            }
        });
        atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 3, false));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeBaseCost(1);
        upgradeSilly(2);
    }
}