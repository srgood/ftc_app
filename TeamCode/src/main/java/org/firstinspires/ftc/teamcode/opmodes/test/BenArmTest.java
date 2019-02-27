package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmodes.base.RoverBase;

/**
 * Created by Derek on 1/9/2019.
 */
@TeleOp(name = "BenArmTest",group = "brbrb")
public class BenArmTest extends RoverBase {
    int lastPosition;
    long lastTime;
    double targetVelocity;

    Servo leftClaw,rightClaw,leftClamp,rightClamp;
    double clawPos;

    @Override
    public void init() {
        super.init();

        leftClaw = hardwareMap.servo.get("clawLeft");
        rightClaw = hardwareMap.servo.get("clawRight");

        leftClamp = hardwareMap.servo.get("leftClamp");
        rightClamp = hardwareMap.servo.get("rightClamp");

        clawPos = 0;

        leftClamp.setPosition(0);
        rightClamp.setPosition(1);

        leftArmBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArmBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArmBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArmBase.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lastTime = -1L;
    }

    @Override
    public void loop() {
        //fixme

        leftClamp.setPosition(0.5);
        rightClamp.setPosition(0.5);

        int currentPos = (leftArmBase.getCurrentPosition() + rightArmBase.getCurrentPosition()) / 2;

        if (lastTime == -1L) {
            lastTime = System.nanoTime();
            lastPosition = currentPos;
            targetVelocity = 0;


            return;
        }

        float tps = ((1000000000L) * (currentPos - lastPosition))/(System.nanoTime() - lastTime);
        long deltaTime = System.nanoTime() - lastTime;

        telemetry.addData("Δt",System.nanoTime() - lastTime);
        telemetry.addData("Δp",currentPos - lastPosition);
        telemetry.addData("tps",tps);
        telemetry.addData("targetVel",targetVelocity);
        telemetry.addData("gamepad2.rightsticky",gamepad2.right_stick_y);
        telemetry.addData("Encoder ticks per second",tps);

        if (gamepad2.right_stick_y == 0) {
            targetVelocity = 0;
        } else {
            if(gamepad2.right_stick_y < 0) {
                if(tps < (900 * -(gamepad2.right_stick_y))){
                    targetVelocity -= 0.01;
                } else {
                    targetVelocity +=0.02;
                }


            } else {

                if(tps > (-900 * (gamepad2.right_stick_y))){
                    targetVelocity += 0.02;
                } else {
                    targetVelocity -=0.01;
                }
            }
        }

        if (targetVelocity >= 1) {
            targetVelocity = 1;
        }

        leftArmBase.setPower(targetVelocity);
        rightArmBase.setPower(targetVelocity);

        //leftArmBase.setPower(gamepad2.right_stick_y / 7);
        //rightArmBase.setPower(gamepad2.right_stick_y / 7);

        leftArmExtender.setPower(gamepad2.left_stick_y/3);
        rightArmExtender.setPower(gamepad2.left_stick_y/3);

        double side    = - gamepad1.left_stick_x / 2;
        double angle   =   gamepad1.right_stick_x / 4;
        double forward =   gamepad1.left_stick_y / 2;

        A.setPower((forward - side) + angle);
        B.setPower((forward + side) + angle);
        C.setPower((forward - side) - angle);
        D.setPower((forward + side) - angle);

        if (gamepad1.dpad_right) {
            clawPos += 0.01;
        }

        if (gamepad1.dpad_left) {
            clawPos -= 0.01;
        }

        rightClaw.setPosition(1-clawPos);
        leftClaw.setPosition(clawPos);


        lastPosition = currentPos;
        lastTime = System.nanoTime();
    }
}
