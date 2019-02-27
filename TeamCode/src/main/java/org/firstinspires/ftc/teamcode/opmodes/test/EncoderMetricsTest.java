package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmodes.base.RoverBase;

/**
 * Created by Derek on 1/30/2019.
 */


@Autonomous(name = "EncoderMetricsTest", group = "group")
public class EncoderMetricsTest extends RoverBase {
    int lastPosition;
    long lastTime;
    double targetVelocity;

    @Override
    public void init() {
        super.init();
        lastTime = -1L;
        targetVelocity = 0;

        B.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        B.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    @Override
    public void loop() {
        super.loop();

        if (lastTime == -1L) {
            lastTime = System.currentTimeMillis();
            lastPosition = B.getCurrentPosition();
            targetVelocity = 0;
            return;
        }

        float tps = ((1000000000L) * (B.getCurrentPosition() - lastPosition))/(System.nanoTime() - lastTime);
        long deltaTime = System.nanoTime() - lastTime;

        telemetry.addData("Δt",System.nanoTime() - lastTime);
        telemetry.addData("Δp",B.getCurrentPosition() - lastPosition);
        telemetry.addData("Encoder ticks per second",tps);
        telemetry.addData("encoder rotations per second",(((1000000000L) * (B.getCurrentPosition() - lastPosition))/(System.nanoTime() - lastTime))/1440);
        telemetry.addData("TargetVelocity",targetVelocity);


        if (tps < 500) {
            targetVelocity += deltaTime * 0.000000007;
        }

        if (tps > 400) {
            targetVelocity -= deltaTime * 0.000000014;
        }

        if (targetVelocity >= 1) {
            targetVelocity = 1;
        }

        B.setPower(targetVelocity);
        lastPosition = B.getCurrentPosition();
        lastTime = System.nanoTime();
    }
}
