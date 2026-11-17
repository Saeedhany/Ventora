# Ventora Documentation Module

## Design decisions
- Each article belongs to exactly one documentation version (Option A).
- Article content is stored as ordered JSON content blocks.
- Table of contents is generated from `HEADING` blocks at response time; it is not stored.
- Admin endpoints end with `/admin`.
- Public endpoints return published articles from active versions/categories only.

## Main endpoints

### Versions
- `GET /documentation/versions`
- `GET /documentation/versions/admin`
- `POST /documentation/versions/admin`
- `PUT /documentation/versions/{id}/admin`
- `PATCH /documentation/versions/{id}/latest/admin`
- `PATCH /documentation/versions/{id}/activate/admin`
- `PATCH /documentation/versions/{id}/deactivate/admin`
- `DELETE /documentation/versions/{id}/admin`

Create a version with `name`, `slug`, optional `description`, and optional `active`. Then select the initial latest version with `PATCH /documentation/versions/{id}/latest/admin`.

### Categories
- `GET /documentation/categories`
- `GET /documentation/categories/admin`
- `POST /documentation/categories/admin`
- `PUT /documentation/categories/{id}/admin`
- `PATCH /documentation/categories/{id}/activate/admin`
- `PATCH /documentation/categories/{id}/deactivate/admin`
- `DELETE /documentation/categories/{id}/admin`

### Articles
- `GET /documentation/articles?version={versionSlug}&category={categorySlug}&page=0&size=10`
- `GET /documentation/articles/{slug}?version={versionSlug}` (omitting version uses the active latest version)
- `GET /documentation/articles/admin?search={text}&version={versionSlug}&page=0&size=10`
- `GET /documentation/articles/{id}/admin`
- `POST /documentation/articles/admin`
- `PUT /documentation/articles/{id}/admin`
- `PATCH /documentation/articles/{id}/publish/admin`
- `PATCH /documentation/articles/{id}/unpublish/admin`
- `DELETE /documentation/articles/{id}/admin`
- `GET /documentation/search?query={text}&version={versionSlug}&page=0&size=10`

Article creation body example:

```json
{
  "title": "Create your account",
  "slug": "create-your-account",
  "description": "Learn how to create your Ventora account.",
  "categoryId": 1,
  "versionId": 1,
  "displayOrder": 1,
  "relatedArticleIds": [],
  "contentBlocks": [
    {"type":"HEADING","content":{"text":"Create your account","level":1},"displayOrder":1},
    {"type":"PARAGRAPH","content":{"text":"Follow these steps to create an account."},"displayOrder":2},
    {"type":"STEPS","content":{"items":["Open the signup page.","Enter your details.","Verify your email."]},"displayOrder":3},
    {"type":"CODE","content":{"language":"java","code":"System.out.println(\"Hello Ventora\");","showLineNumbers":true},"displayOrder":4}
  ]
}
```

Supported block types: `HEADING`, `PARAGRAPH`, `IMAGE`, `CODE`, `LIST`, `STEPS`, `CALLOUT`.

The article response includes `tableOfContents`, `relatedArticles`, `previousArticle`, and `nextArticle`. Previous/Next are scoped to the same version and category.

### Page content
- `GET /documentation/page-content`
- `PUT /documentation/page-content/admin`

Fields: `heroTitle`, `heroDescription`, `searchPlaceholder`, `needHelpTitle`, `needHelpDescription`, `getStartedLabel`, `getStartedUrl`.

## Database
The project currently uses `spring.jpa.hibernate.ddl-auto=update`, so Hibernate should create/update the documentation tables when the application starts. Back up the database before running schema updates in a non-development environment.

## Notes
- Create at least one version and mark it latest before using public article endpoints without a `version` query parameter.
- Related articles must belong to the same version and cannot point to the article itself.
- A category/version cannot be deleted while articles reference it.
- A latest version cannot be deactivated unless another active version exists; that other version is promoted automatically.
- Article publishing requires an active version, active category, title, and at least one content block.
