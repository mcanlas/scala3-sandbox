trait Contravariant[F[_]]:
  def contramap[A, B](fa: F[A])(f: B => A): F[B]

trait Encoder[Z, A]

object Encoder:
  given Encoder[String, String] =
    new Encoder[String, String] {}

  // if you change the trait from Encoder[Z, *] to Encoder[*, Z] (properly everywhere)
  //   this lookup starts to fail. why?
  //   does the position of the type hole influence lookup?
  given [Z]: Contravariant[Encoder[Z, *]] with
    def contramap[A, B](fa: Encoder[Z, A])(f: B => A): Encoder[Z, B] =
      new Encoder[Z, B] { }

  extension[F[_] : Contravariant, A] (fa: F[A])
    def demandContravariance: Unit =
      println("done!!")

object ContraVariantSandboxApp extends App:
  summon[Encoder[String, String]]
    .demandContravariance
