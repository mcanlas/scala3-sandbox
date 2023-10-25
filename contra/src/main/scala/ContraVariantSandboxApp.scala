trait Contravariant[F[_]]:
  def contramap[A, B](fa: F[A])(f: B => A): F[B]

trait Encoder[Z, A]

object Encoder:
  given Encoder[String, String] =
    new Encoder[String, String] {}

  // when written Encoder[*, Z], this lookup starts to fail. why?
  given [Z]: Contravariant[Encoder[Z, *]] with
    def contramap[A, B](fa: Encoder[Z, A])(f: B => A): Encoder[Z, B] =
      new Encoder[Z, B] { }

  extension[F[_] : Contravariant, A] (fa: F[A])
    def demandContravariance: Unit =
      println("done!!")

object ContraVariantSandboxApp extends App:
  summon[Encoder[String, String]]
    .demandContravariance
