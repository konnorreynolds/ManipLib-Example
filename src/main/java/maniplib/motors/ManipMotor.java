package maniplib.motors;

import com.revrobotics.spark.SparkBase;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj2.command.Command;
import swervelib.parser.PIDFConfig;

/**
 * Swerve motor abstraction which defines a standard interface for motors within a swerve module.
 */
public abstract class ManipMotor {

    /**
     * The maximum amount of times the swerve motor will attempt to configure a motor if failures occur.
     */
    public final int maximumRetries = 5;
    /**
     * Sim motor to use, defaulted in {@link ManipMotor#getSimMotor()}, but can be overridden here. <br/> NOTE: This will
     * not change the simulation motor type! It is intended for use only if you are utilizing Feedforwards from
     * PathPlanner.
     */
    public DCMotor simMotor;

    /**
     * Configure the factory defaults.
     */
    public abstract void factoryDefaults();

    /**
     * Clear the sticky faults on the motor controller.
     */
    public abstract void clearStickyFaults();

    /**
     * Configure the PIDF values for the closed loop controller. 0 is disabled or off.
     *
     * @param config Configuration class holding the PIDF values.
     */
    public abstract void configurePIDF(PIDFConfig config);

    /**
     * Configure the PID wrapping for the position closed loop controller.
     *
     * @param minInput Minimum PID input.
     * @param maxInput Maximum PID input.
     */
    public abstract void configurePIDWrapping(double minInput, double maxInput);

    /**
     * Set the idle mode.
     *
     * @param isBrakeMode Set the brake mode.
     */
    public abstract void setMotorBrake(boolean isBrakeMode);

    /**
     * Set the motor to be inverted.
     *
     * @param inverted State of inversion.
     */
    public abstract void setInverted(boolean inverted);

    /**
     * Save the configurations from flash to EEPROM.
     */
    public abstract void burnFlash();

    /**
     * Sets the {@link ManipMotor} to follow another {@link ManipMotor}.
     *
     * @param leadMotor  lead {@link ManipMotor} to follow.
     * @param isInverted whether to invert the follower or not.
     */
    public abstract void setAsFollower(ManipMotor leadMotor, Boolean isInverted);

    /**
     * Set the percentage output.
     *
     * @param percentOutput percent out for the motor controller.
     */
    public abstract void set(double percentOutput);

    /**
     * Set the closed loop PID controller reference point.
     *
     * @param setpoint    Setpoint, value type changes with ControlType.
     * @param feedforward Feedforward in volt-meter-per-second or kV.
     * @param controlType ControlType to run the setReference as.
     */
    public abstract void setReference(double setpoint, double feedforward, SparkBase.ControlType controlType);

    /**
     * Set the closed loop PID controller reference point.
     *
     * @param setpoint    Setpoint, value type changes with ControlType.
     * @param controlType ControlType to run the setReference as.
     */
    public abstract void setReference(double setpoint, SparkBase.ControlType controlType);

    /**
     * Set the closed loop PID controller reference point.
     *
     * @param setpoint    Setpoint in meters per second or angle in degrees.
     * @param feedforward Feedforward in volt-meter-per-second or kV.
     */
    public abstract void setReference(double setpoint, double feedforward);

    /**
     * Set the closed loop PID controller reference point.
     *
     * @param setpoint Setpoint in meters per second or angle in degrees.
     */
    public abstract void setReference(double setpoint);

    /**
     * Stop the motor.
     */
    public abstract void stopMotor();

    /**
     * A command to stop the motor.
     *
     * @return a command to stop the motor.
     */
    public abstract Command stopMotorCommand();

    /**
     * Get the voltage output of the motor controller.
     *
     * @return Voltage output.
     */
    public abstract double getVoltage();

    /**
     * Set the voltage of the motor.
     *
     * @param voltage Voltage to set.
     */
    public abstract void setVoltage(double voltage);

    public abstract int getMotorID();

    /**
     * Get the applied dutycycle output.
     *
     * @return Applied dutycycle output to the motor.
     */
    public abstract double getAppliedOutput();

    /**
     * Get the velocity of the integrated encoder.
     *
     * @return velocity in meters per second or degrees per second.
     */
    public abstract double getVelocity();

    /**
     * Get the position of the integrated encoder.
     *
     * @return Position in meters or degrees.
     */
    public abstract double getPosition();

    /**
     * Set the integrated encoder position.
     *
     * @param position Integrated encoder position. Should be angle in degrees or meters per second.
     */
    public abstract void setPosition(double position);

    /**
     * Set the voltage compensation for the swerve module motor.
     *
     * @param nominalVoltage Nominal voltage for operation to output to.
     */
    public abstract void setVoltageCompensation(double nominalVoltage);

    /**
     * Set the current limit for the swerve drive motor, remember this may cause jumping if used in conjunction with
     * voltage compensation. This is useful to protect the motor from current spikes.
     *
     * @param currentLimit Current limit in AMPS at free speed.
     */
    public abstract void setCurrentLimit(int currentLimit);

    /**
     * Set the maximum rate the open/closed loop output can change by.
     *
     * @param rampRate Time in seconds to go from 0 to full throttle.
     */
    public abstract void setLoopRampRate(double rampRate);

    /**
     * Get the motor object from the module.
     *
     * @return Motor object.
     */
    public abstract Object getMotor();

    /**
     * Get the {@link DCMotor} of the motor class.
     *
     * @return {@link DCMotor} of this type.
     */
    public abstract DCMotor getSimMotor();
}
