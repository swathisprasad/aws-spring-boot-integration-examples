provider "aws" {
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
  region = var.region
}

resource "aws_s3_bucket" "demo-s3" {
  bucket = var.bucket_name
}

resource "aws_s3_bucket_public_access_block" "demo-policy" {
  count = 1

  bucket = aws_s3_bucket.demo-s3.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true

  depends_on = [aws_s3_bucket.demo-s3]
}
