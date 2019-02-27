package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by Derek on 11/14/2017.
 */

@TeleOp(group = "Test",name = "MeccanumTest")
public class TestMeccanumOp extends OpMode{
    private DcMotor A,B,C,D,liftMotor;
    @Override
    public void init() {
        try {
            B = hardwareMap.dcMotor.get("frontLeft");
            C = hardwareMap.dcMotor.get("frontRight");
            A = hardwareMap.dcMotor.get("backLeft");
            D = hardwareMap.dcMotor.get("backRight");
            liftMotor = hardwareMap.dcMotor.get("arm");

            C.setDirection(REVERSE);
            D.setDirection(REVERSE);
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            telemetry.addData("",e.getLocalizedMessage());
        }
    }

    @Override
    public void loop() {
        double side    = - gamepad1.left_stick_x / 2;
        double angle   =   gamepad1.right_stick_x / 4;
        double forward =   gamepad1.left_stick_y / 2;
        liftMotor.setPower(gamepad2.right_stick_y);

        A.setPower((forward - side) + angle) ;
        B.setPower((forward + side) + angle);
        C.setPower((forward - side) - angle);
        D.setPower((forward + side) - angle);
    }
}
